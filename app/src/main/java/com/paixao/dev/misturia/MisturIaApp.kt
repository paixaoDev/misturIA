package com.paixao.dev.misturia

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paixao.dev.misturia.presentation.screens.HomeScreen
import com.paixao.dev.misturia.presentation.screens.IngredientConfigScreen

@Composable
fun MisturIaApp() {
    val navController = rememberNavController()
    MisturIaNavHost(navController)
}

@Composable
fun MisturIaNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("list") {
            IngredientConfigScreen()
        }
    }
}
