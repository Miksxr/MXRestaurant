package com.example.mxrestaurant.data.repository

import com.example.mxrestaurant.data.remote.SupabaseClientInstance
import com.example.mxrestaurant.domain.model.User
import com.example.mxrestaurant.domain.model.UserRole
import com.example.mxrestaurant.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AuthRepositoryImpl : AuthRepository {

    private val client = SupabaseClientInstance.client

    override suspend fun signUp(email: String, password: String, role: UserRole): User {
        val result = client.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }

        val user = client.auth.currentUserOrNull() ?: throw Exception("Пользователь не получен")
        val userId = user.id
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

        when (role) {
            UserRole.VISITOR -> {
                client.from("visitors").insert(
                    mapOf(
                        "email" to email,
                        "password_hash" to "",
                        "first_name" to "Имя",
                        "last_name" to "Фамилия",
                        "phone" to "0000000000",
                        "registration_date" to now.toString()
                    )
                )
            }

            UserRole.WAITER -> {
                client.from("waiters").insert(
                    mapOf(
                        "email" to email,
                        "first_name" to "Имя",
                        "last_name" to "Фамилия",
                        "phone" to "0000000000",
                        "hire_date" to now.toString(),
                        "status" to "active"
                    )
                )
            }

            UserRole.ADMIN -> {
                client.from("admins").insert(
                    mapOf(
                        "email" to email,
                        "first_name" to "Имя",
                        "last_name" to "Фамилия",
                        "phone" to "0000000000",
                        "hire_date" to now.toString(),
                        "status" to "active"
                    )
                )
            }
        }

        return User(id = userId, email = email, role = role)
    }

    override suspend fun signIn(email: String, password: String): User {
        val result = client.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }

        val user = client.auth.currentUserOrNull() ?: throw Exception("Ошибка: пользователь не найден")
        val userId = user.id

        val role = when {
            client.from("visitors").select {
                filter { eq("email", email) }
            }.decodeList<Map<String, Any>>().isNotEmpty() -> UserRole.VISITOR

            client.from("waiters").select {
                filter { eq("email", email) }
            }.decodeList<Map<String, Any>>().isNotEmpty() -> UserRole.WAITER

            client.from("admins").select {
                filter { eq("email", email) }
            }.decodeList<Map<String, Any>>().isNotEmpty() -> UserRole.ADMIN

            else -> throw Exception("Роль не найдена")
        }

        return User(id = user.id, email = email, role = role)
    }

    override suspend fun signOut() {
        client.auth.signOut()
    }
}