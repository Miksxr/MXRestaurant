package com.example.mxrestaurant.data.repository

import com.example.mxrestaurant.data.mapper.toDomain
import com.example.mxrestaurant.data.model.TableDto
import com.example.mxrestaurant.data.remote.SupabaseClientInstance
import com.example.mxrestaurant.domain.model.Table
import com.example.mxrestaurant.domain.repository.ReservationRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.time.LocalDate

class ReservationRepositoryImpl : ReservationRepository {

    private val client = SupabaseClientInstance.client

    override suspend fun getAvailableTables(date: LocalDate): List<Table> {
        val response = client.postgrest["tables"]
            .select()
            .decodeList<TableDto>() // uses kotlinx.serialization

        return response
            .filter { it.status == "free" } // можно адаптировать под значения в твоей БД
            .map { it.toDomain() }
    }

    override suspend fun createReservation(tableId: Int, date: LocalDate) {
        val userId = client.auth.currentUserOrNull()?.id ?: throw Exception("Неавторизован")

        val body: JsonObject = buildJsonObject {
            put("visitor_id", userId)
            put("table_id", tableId)
            put("reservation_date", date.toString())
            put("start_time", "18:00:00") // временно захардкожено
            put("duration", 2)
            put("end_time", "20:00:00")
            put("status", "активно")
        }

        client.postgrest["reservations"]
            .insert(body)
    }
}