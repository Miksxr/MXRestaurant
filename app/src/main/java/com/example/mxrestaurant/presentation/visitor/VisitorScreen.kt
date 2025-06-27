package com.example.mxrestaurant.presentation.visitor

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VisitorScreen(
    viewModel: VisitorViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DatePickerField(
            label = "Выберите дату",
            selectedDate = selectedDate,
            onDateSelected = {
                selectedDate = it
                viewModel.getAvailableTables(it)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Свободные столы на ${selectedDate}:", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text("Ошибка: ${state.error}", color = MaterialTheme.colorScheme.error)
            }

            else -> {
                LazyColumn {
                    items(state.availableTables.size) { index ->
                        val table = state.availableTables[index]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    viewModel.createReservation(table.id, selectedDate) { success ->
                                        if (success) {
                                            Toast.makeText(
                                                context,
                                                "Бронирование успешно",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Ошибка при бронировании",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Стол #${table.number}", style = MaterialTheme.typography.bodyLarge)
                                Text("Мест: ${table.capacity}", style = MaterialTheme.typography.bodySmall)
                                Text("Расположение: ${table.location}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}