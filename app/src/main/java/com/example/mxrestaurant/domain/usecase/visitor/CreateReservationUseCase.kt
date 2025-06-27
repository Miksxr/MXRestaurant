package com.example.mxrestaurant.domain.usecase.visitor

import com.example.mxrestaurant.domain.repository.ReservationRepository
import java.time.LocalDate

class CreateReservationUseCase(
    private val repository: ReservationRepository
) {
    suspend operator fun invoke(tableId: Int, date: LocalDate) {
        return repository.createReservation(tableId, date)
    }
}