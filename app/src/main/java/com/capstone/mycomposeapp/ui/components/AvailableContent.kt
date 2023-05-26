package com.capstone.mycomposeapp.ui.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.capstone.mycomposeapp.model.FavoriteMovie


@Composable
fun AvailableContent(
    movies: List<FavoriteMovie>,
    navController: NavController,
    scaffoldState: ScaffoldState,

    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(movies, key = {it.movie.id}){
                MovieListItem(it, navController, scaffoldState)
            }
        }
}