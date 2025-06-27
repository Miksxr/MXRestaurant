package com.example.mxrestaurant.domain.usecase.auth

import com.example.mxrestaurant.domain.model.User
import com.example.mxrestaurant.domain.model.UserRole
import com.example.mxrestaurant.domain.repository.AuthRepository

class SignUpUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        role: UserRole
    ): User {
        return repository.signUp(email, password, role)
    }
}