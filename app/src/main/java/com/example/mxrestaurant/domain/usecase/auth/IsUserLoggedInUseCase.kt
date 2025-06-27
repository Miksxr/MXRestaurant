package com.example.mxrestaurant.domain.usecase.auth

import com.example.mxrestaurant.domain.repository.AuthRepository

class IsUserLoggedInUseCase(
    private val repository: AuthRepository
) {

}