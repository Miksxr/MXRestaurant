package com.example.mxrestaurant.domain.usecase.visitor

import com.example.mxrestaurant.domain.model.Table
import com.example.mxrestaurant.domain.repository.ReservationRepository

class GetAvailableTablesUseCase(
    private val repository: ReservationRepository
) {
    suspend operator fun invoke(date: java.time.LocalDate): List<Table> {
        return repository.getAvailableTables(date)
    }
}