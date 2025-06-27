package com.example.mxrestaurant.presentation.visitor

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerField(
    label: String,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current

    OutlinedTextField(
        value = selectedDate.toString(),
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val date = selectedDate
                val dialog = DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val picked = LocalDate.of(year, month + 1, dayOfMonth)
                        onDateSelected(picked)
                    },
                    date.year,
                    date.monthValue - 1,
                    date.dayOfMonth
                )
                dialog.show()
            }
    )
}
