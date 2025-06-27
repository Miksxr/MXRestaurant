package com.example.mxrestaurant.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class UserRole {
    VISITOR, WAITER, ADMIN
}