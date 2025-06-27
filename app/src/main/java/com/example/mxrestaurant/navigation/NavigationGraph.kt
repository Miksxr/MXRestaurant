package com.example.mxrestaurant.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mxrestaurant.domain.model.UserRole
import com.example.mxrestaurant.presentation.AdminHomeScreen
import com.example.mxrestaurant.presentation.WaiterHomeScreen
import com.example.mxrestaurant.presentation.auth.AuthScreen
import com.example.mxrestaurant.presentation.auth.AuthViewModel
import com.example.mxrestaurant.presentation.visitor.VisitorScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.AUTH,
        modifier = modifier
    ) {
        composable(NavRoutes.AUTH) {
            AuthScreen(viewModel = authViewModel) { role ->
                when (role) {
                    UserRole.VISITOR -> navController.navigate(NavRoutes.VISITOR_HOME) {
                        popUpTo(NavRoutes.AUTH) { inclusive = true }
                    }
                    UserRole.WAITER -> navController.navigate(NavRoutes.WAITER_HOME) {
                        popUpTo(NavRoutes.AUTH) { inclusive = true }
                    }
                    UserRole.ADMIN -> navController.navigate(NavRoutes.ADMIN_HOME) {
                        popUpTo(NavRoutes.AUTH) { inclusive = true }
                    }
                }
            }
        }

        composable(NavRoutes.VISITOR_HOME) {
            VisitorScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.WAITER_HOME) {
            WaiterHomeScreen()
        }

        composable(NavRoutes.ADMIN_HOME) {
            AdminHomeScreen()
        }
    }
}