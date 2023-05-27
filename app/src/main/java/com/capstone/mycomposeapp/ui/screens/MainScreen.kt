package com.capstone.mycomposeapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstone.mycomposeapp.ui.navigation.Screen

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.capstone.mycomposeapp.ui.navigation.NavigationItem
import com.capstone.mycomposeapp.ui.screens.detail.DetailMovieScreen
import com.capstone.mycomposeapp.ui.screens.favorite.FavoriteScreen
import com.capstone.mycomposeapp.ui.screens.home.HomeScreen
import com.capstone.mycomposeapp.ui.screens.profile.ProfileScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController, currentRoute)
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(snackbarData = data, shape = RoundedCornerShape(8.dp))
            }
        },
        modifier = modifier,
    ){
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ){
            composable(Screen.Home.route){
                HomeScreen(navigateToDetail = { rewardId ->
                    navController.navigate(Screen.Detail.createRoute(rewardId))
                }, scaffoldState = scaffoldState)
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("movieId"){
                        type = NavType.IntType
                    }
                )
            ){
                val movieId = it.arguments?.getInt("movieId") ?: 0
                DetailMovieScreen(movieId = movieId, scaffoldState)
            }
            composable(Screen.Favorite.route){
                FavoriteScreen(navController, scaffoldState)
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
        }
    }
}

@Composable
fun BottomBar(
    navHostController: NavHostController,
    currentRoute: String?,
) {
    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Rounded.Home,
            screens = Screen.Home
        ),
        NavigationItem(
            title = "Favorite",
            icon = Icons.Rounded.Favorite,
            screens = Screen.Favorite
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Rounded.Person,
            screens = Screen.Profile
        ),
    )

    BottomNavigation(backgroundColor = Color(0xFFF6F7F8)) {
        navigationItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.screens.route,
                onClick = {
                    navHostController.navigate(item.screens.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = Color.Gray,
            )
        }
    }
}
