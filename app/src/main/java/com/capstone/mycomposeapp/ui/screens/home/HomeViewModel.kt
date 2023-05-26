package com.capstone.mycomposeapp.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.mycomposeapp.data.MovieRepository
import com.capstone.mycomposeapp.model.FavoriteMovie
import com.capstone.mycomposeapp.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<FavoriteMovie>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<FavoriteMovie>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query


    fun getAllMovies() {
        viewModelScope.launch {
            repository.getAllMovies().catch {
                _uiState.value = UIState.Error(it.message.toString())
            }.collect { orderRewards ->
                _uiState.value = UIState.Success(orderRewards)
            }
        }
    }


}