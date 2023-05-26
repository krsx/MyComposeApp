package com.capstone.mycomposeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.capstone.mycomposeapp.model.FavoriteMovie

@Composable
fun MovieContent(
    listMovies: List<FavoriteMovie>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String? = null,
    onQueryChange: ((String) -> Unit)? = null,

) {
    Column {
        if (query != null && onQueryChange != null) {
            SearchBar(query = query, onQueryChange = onQueryChange)
        }
        when (listMovies.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(
                listMovies,
                navController,
                scaffoldState,
            )
        }
    }
}