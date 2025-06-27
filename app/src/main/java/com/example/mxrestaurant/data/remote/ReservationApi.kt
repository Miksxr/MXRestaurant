package com.example.mxrestaurant.data.remote

import android.util.Log
import com.example.mxrestaurant.data.model.ReservationDto
import com.example.mxrestaurant.data.model.TableDto
import io.github.jan.supabase.postgrest.postgrest

object ReservationApi {
    suspend fun createReservation(dto: ReservationDto) {
        SupabaseClientInstance.client.postgrest.from("reservations").insert(dto)
    }

    suspend fun getAvailableTables(date: String, time: String): List<Int> {
        val response = SupabaseClientInstance.client.postgrest
            .from("tables")
            .select()
            .decodeList<TableDto>()
        Log.d("ReservationApi", "Получено столов: ${response.size}")
        Log.d("ReservationApi", "Свободные: ${response.filter { it.status == "free" }}")

        return response.filter { it.status == "free" }.map { it.id }
    }
}