package com.example.mxrestaurant.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TableDto(
    val id: Int,
    val number: Int,
    val capacity: Int,
    val status: String,
    val location: String
)