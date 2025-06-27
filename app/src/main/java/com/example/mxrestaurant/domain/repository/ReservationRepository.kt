package com.example.mxrestaurant.domain.repository

import com.example.mxrestaurant.domain.model.Table
import java.time.LocalDate

interface ReservationRepository {

    suspend fun getAvailableTables(date: LocalDate): List<Table>

    suspend fun createReservation(tableId: Int, date: LocalDate)
}