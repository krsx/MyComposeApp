package com.capstone.mycomposeapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Detail : Screen("home/{movieId}") {
        fun createRoute(movieId: Int) = "home/$movieId"
    }
    object Profile : Screen("profile")
}