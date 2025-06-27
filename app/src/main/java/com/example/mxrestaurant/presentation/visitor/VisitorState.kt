package com.example.mxrestaurant.presentation.visitor

import com.example.mxrestaurant.domain.model.Table

data class VisitorState(
    val isLoading: Boolean = false,
    val availableTables: List<Table> = emptyList(),
    val error: String? = null
)