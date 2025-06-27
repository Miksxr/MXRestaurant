package com.example.mxrestaurant.domain.repository

import com.example.mxrestaurant.domain.model.User
import com.example.mxrestaurant.domain.model.UserRole

interface AuthRepository {
    suspend fun signUp(email: String, password: String, role: UserRole): User
    suspend fun signIn(email: String, password: String): User
    suspend fun signOut()
}