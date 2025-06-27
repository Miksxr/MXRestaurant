package com.example.mxrestaurant.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Table(
    val id: Int,
    val number: Int,
    val capacity: Int,
    val status: String,
    val location: String
)