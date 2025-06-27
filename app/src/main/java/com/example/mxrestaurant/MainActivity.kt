package com.example.mxrestaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.mxrestaurant.navigation.NavigationGraph
import com.example.mxrestaurant.presentation.auth.AuthViewModel
import com.example.mxrestaurant.ui.theme.MXRestaurantTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MXRestaurantTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel by viewModels()

                NavigationGraph(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }
    }
}
