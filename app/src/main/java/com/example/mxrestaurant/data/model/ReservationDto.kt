package com.example.mxrestaurant.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ReservationDto(
    val id: Long,
    val visitor_id: Int,
    val table_id: Int,
    val reservation_date: String,
    val start_time: String,
    val duration: Int,
    val end_time: String,
    val status: String,
    val created_at: String
)