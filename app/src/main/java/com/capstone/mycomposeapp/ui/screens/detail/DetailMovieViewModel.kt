package com.capstone.mycomposeapp.ui.screens.detail

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

class DetailMovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState<FavoriteMovie>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<FavoriteMovie>>
        get() = _uiState

    fun getMovieById(id: Int){
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            _uiState.value = UIState.Success(repository.getMovieDetail(id))
        }
    }

    fun updateFavoriteMovie(id: Int, isFavoriteMovie: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteMovieById(id, isFavoriteMovie)
        }
    }
}