package com.example.mxrestaurant.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String,
    val role: UserRole
)