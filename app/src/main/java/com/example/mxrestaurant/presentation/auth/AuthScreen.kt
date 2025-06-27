package com.example.mxrestaurant.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mxrestaurant.domain.model.UserRole

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onAuthSuccess: (UserRole) -> Unit
) {
    val state by viewModel.state.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignUp by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf(UserRole.VISITOR) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (isSignUp) "Регистрация" else "Вход", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (isSignUp) {
            Text("Выберите роль:")
            Spacer(modifier = Modifier.height(4.dp))
            UserRole.values().forEach { role ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedRole == role,
                        onClick = { selectedRole = role }
                    )
                    Text(text = role.name)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (isSignUp) {
                    viewModel.signUp(email.trim(), password.trim(), selectedRole)
                } else {
                    viewModel.signIn(email.trim(), password.trim())
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (isSignUp) "Зарегистрироваться" else "Войти")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            isSignUp = !isSignUp
            viewModel.resetState()
        }) {
            Text(text = if (isSignUp) "Уже есть аккаунт? Войти" else "Нет аккаунта? Зарегистрироваться")
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (state) {
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Error -> Text(
                text = (state as AuthState.Error).message,
                color = MaterialTheme.colorScheme.error
            )
            is AuthState.Success -> {
                onAuthSuccess((state as AuthState.Success).user.role)
                viewModel.resetState()
            }
            else -> Unit
        }
    }
}