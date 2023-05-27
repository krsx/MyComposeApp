package com.capstone.mycomposeapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.capstone.mycomposeapp.model.FavoriteMovie


@Composable
fun AvailableContent(
    modifier: Modifier,
    movies: List<FavoriteMovie>,
    scaffoldState: ScaffoldState,
    navigateToDetail: (Int) -> Unit,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
        items(movies, key = { it.movie.id }) {
            MovieListItem(
                it,
                modifier = Modifier.clickable {
                    navigateToDetail(it.movie.id)
                },
                scaffoldState, onUpdateFavoriteMovie,
            )
        }
    }
}