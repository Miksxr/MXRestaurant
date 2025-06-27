package com.example.mxrestaurant.domain.usecase.auth

import com.example.mxrestaurant.domain.repository.AuthRepository

class SignOutUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.signOut()
}