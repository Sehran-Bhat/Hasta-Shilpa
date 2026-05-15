package com.example.hastashilpa.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object DesignDetail : Screen("design_detail/{productId}") {
        fun createRoute(productId: String) = "design_detail/$productId"
    }
    object Blueprint : Screen("blueprint/{productId}") {
        fun createRoute(productId: String) = "blueprint/$productId"
    }
    object Materials : Screen("materials")
    object PriceSuggestion : Screen("price_suggestion")
    object Marketplace : Screen("marketplace")
    object Profile : Screen("profile")
}
