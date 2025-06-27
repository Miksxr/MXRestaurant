package com.example.mxrestaurant.domain.usecase.auth

import com.example.mxrestaurant.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.mxrestaurant.domain.usecase.auth.SignInUseCase
import com.example.mxrestaurant.domain.usecase.auth.SignOutUseCase
import com.example.mxrestaurant.domain.usecase.auth.SignUpUseCase

data class AuthUseCase(
    val signIn: SignInUseCase,
    val signUp: SignUpUseCase,
    val signOut: SignOutUseCase,
    val isUserLoggedIn: IsUserLoggedInUseCase
)