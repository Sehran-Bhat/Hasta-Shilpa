package com.example.hastashilpa.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hastashilpa.navigation.Screen

sealed class BottomNavItem(val titleResId: Int, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem(com.example.hastashilpa.R.string.home, Icons.Default.Home, Screen.Home.route)
    object Marketplace : BottomNavItem(com.example.hastashilpa.R.string.marketplace, Icons.Default.ShoppingCart, Screen.Marketplace.route)
    object Materials : BottomNavItem(com.example.hastashilpa.R.string.track_material, Icons.Default.Build, Screen.Materials.route)
    object Profile : BottomNavItem(com.example.hastashilpa.R.string.profile, Icons.Default.Person, Screen.Profile.route)
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Marketplace,
        BottomNavItem.Materials,
        BottomNavItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomNav = items.any { it.route == currentDestination?.route }

    if (showBottomNav) {
        NavigationBar {
            items.forEach { item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = androidx.compose.ui.res.stringResource(item.titleResId)) },
                    label = { Text(androidx.compose.ui.res.stringResource(item.titleResId)) },
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
