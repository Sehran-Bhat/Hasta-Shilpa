package com.example.hastashilpa.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hastashilpa.ui.components.BottomNavBar
import com.example.hastashilpa.ui.screens.*
import com.example.hastashilpa.viewmodel.HastaShilpaViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: HastaShilpaViewModel = viewModel()
    
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(navController = navController, viewModel = viewModel)
            }
            composable(
                route = Screen.DesignDetail.route,
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId") ?: ""
                DesignDetailScreen(navController = navController, viewModel = viewModel, productId = productId)
            }
            composable(
                route = Screen.Blueprint.route,
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId") ?: ""
                BlueprintScreen(navController = navController, viewModel = viewModel, productId = productId)
            }
            composable(Screen.Materials.route) {
                MaterialTrackerScreen(navController = navController, viewModel = viewModel)
            }
            composable(Screen.PriceSuggestion.route) {
                PriceSuggestionScreen(navController = navController, viewModel = viewModel)
            }
            composable(Screen.Marketplace.route) {
                MarketplaceScreen(navController = navController, viewModel = viewModel)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}
