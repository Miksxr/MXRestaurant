package com.example.mxrestaurant.presentation.visitor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mxrestaurant.domain.usecase.visitor.CreateReservationUseCase
import com.example.mxrestaurant.domain.usecase.visitor.GetAvailableTablesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class VisitorViewModel @Inject constructor(
    private val getAvailableTablesUseCase: GetAvailableTablesUseCase,
    private val createReservationUseCase: CreateReservationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(VisitorState())
    val state: StateFlow<VisitorState> = _state

    fun getAvailableTables(date: LocalDate) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val tables = getAvailableTablesUseCase(date)
                _state.value = _state.value.copy(isLoading = false, availableTables = tables)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun createReservation(tableId: Int, date: LocalDate, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                createReservationUseCase(tableId, date)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}