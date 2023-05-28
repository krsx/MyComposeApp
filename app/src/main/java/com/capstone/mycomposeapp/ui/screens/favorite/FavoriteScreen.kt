package com.capstone.mycomposeapp.ui.screens.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.mycomposeapp.injection.Injection
import com.capstone.mycomposeapp.ui.components.ErrorContent
import com.capstone.mycomposeapp.ui.components.Loading
import com.capstone.mycomposeapp.ui.components.MovieContent
import com.capstone.mycomposeapp.ui.screens.ViewModelFactory
import com.capstone.mycomposeapp.utils.UIState

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    navigateToDetail: (Int) -> Unit,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { UIState ->
        when (UIState) {
            is UIState.Error -> ErrorContent()
            is UIState.Loading -> {
                Loading()
                viewModel.getAllFavoriteMovies()
            }
            is UIState.Success -> MovieContent(
                modifier,
                listMovies = UIState.data,
                navigateToDetail = navigateToDetail,
                scaffoldState = scaffoldState,
                onUpdateFavoriteMovie = { id, isFavorite ->
                    viewModel.updateFavoriteMovie(id, isFavorite)
                }
            )
        }
    }

}