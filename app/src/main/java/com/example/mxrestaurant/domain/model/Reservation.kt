package com.example.mxrestaurant.domain.model

data class Reservation(
    val id: Int? = null,
    val visitorId: Int,
    val tableId: Int,
    val date: String,
    val startTime: String,
    val duration: Int,
    val endTime: String,
    val status: String
)