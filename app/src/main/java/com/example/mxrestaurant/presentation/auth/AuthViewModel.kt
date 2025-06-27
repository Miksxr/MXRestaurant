package com.example.mxrestaurant.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mxrestaurant.domain.model.UserRole
import com.example.mxrestaurant.domain.usecase.auth.SignInUseCase
import com.example.mxrestaurant.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state

    fun signUp(email: String, password: String, role: UserRole) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val user = signUpUseCase(email, password, role)
                _state.value = AuthState.Success(user)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val user = signInUseCase(email, password)
                _state.value = AuthState.Success(user)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _state.value = AuthState.Idle
    }
}