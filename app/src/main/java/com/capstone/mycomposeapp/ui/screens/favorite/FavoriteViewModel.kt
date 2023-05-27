package com.capstone.mycomposeapp.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.mycomposeapp.data.MovieRepository
import com.capstone.mycomposeapp.model.FavoriteMovie
import com.capstone.mycomposeapp.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<FavoriteMovie>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<FavoriteMovie>>> get() = _uiState

    init {
        getAllFavoriteMovies()
    }

    fun getAllFavoriteMovies() {
        viewModelScope.launch {
            repository.getAllFavoritesMovies().catch {
                _uiState.value = UIState.Error(it.message.toString())
            }.collect { movies ->
                _uiState.value = UIState.Success(movies)
            }
        }
    }

    fun updateFavoriteMovie(id: Int, isFavoriteMovie: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteMovieById(id, isFavoriteMovie)
            getAllFavoriteMovies()
        }
    }
}
