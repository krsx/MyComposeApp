package com.capstone.mycomposeapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Detail : Screen("home/{id}") {
        fun createRoute(hotelId: Int) = "home/$hotelId"
    }

    object Profile : Screen("profile")
}