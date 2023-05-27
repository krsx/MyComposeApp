package com.capstone.mycomposeapp.ui.screens.detail

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.mycomposeapp.injection.Injection
import com.capstone.mycomposeapp.ui.components.DetailMovieContent
import com.capstone.mycomposeapp.ui.components.ErrorContent
import com.capstone.mycomposeapp.ui.components.Loading
import com.capstone.mycomposeapp.ui.screens.ViewModelFactory
import com.capstone.mycomposeapp.ui.screens.home.HomeViewModel
import com.capstone.mycomposeapp.utils.UIState

@Composable
fun DetailMovieScreen(
    movieId: Int, scaffoldState: ScaffoldState, viewModel: DetailMovieViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Error -> ErrorContent()
            is UIState.Loading -> viewModel.getMovieById(movieId)
            is UIState.Success -> DetailMovieContent(
                movie = uiState.data,
                scaffoldState = scaffoldState,
                onUpdateFavoriteMovie = viewModel::updateFavoriteMovie
            )
        }
    }
}