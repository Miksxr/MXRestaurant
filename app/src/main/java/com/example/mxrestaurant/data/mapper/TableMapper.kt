package com.example.mxrestaurant.data.mapper

import com.example.mxrestaurant.data.model.TableDto
import com.example.mxrestaurant.domain.model.Table

fun TableDto.toDomain(): Table {
    return Table(
        id = id,
        number = number,
        capacity = capacity,
        status = status,
        location = location
    )
}