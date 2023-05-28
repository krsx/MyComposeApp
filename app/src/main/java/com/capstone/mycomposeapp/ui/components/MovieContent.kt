package com.capstone.mycomposeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.capstone.mycomposeapp.model.FavoriteMovie

@Composable
fun MovieContent(
    modifier: Modifier,
    listMovies: List<FavoriteMovie>,
    scaffoldState: ScaffoldState,
    navigateToDetail: (Int) -> Unit,
    query: String? = null,
    onQueryChange: ((String) -> Unit)? = null,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
) {
    Column {
        if (query != null && onQueryChange != null) {
            SearchBar(query = query, onQueryChange = onQueryChange)
        }
        when (listMovies.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(
                modifier,
                listMovies,
                scaffoldState,
                navigateToDetail,
                onUpdateFavoriteMovie,
            )
        }
    }
}