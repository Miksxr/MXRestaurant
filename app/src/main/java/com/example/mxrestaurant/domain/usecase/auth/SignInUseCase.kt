package com.example.mxrestaurant.domain.usecase.auth

import com.example.mxrestaurant.domain.model.User
import com.example.mxrestaurant.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): User {
        return repository.signIn(email, password)
    }
}