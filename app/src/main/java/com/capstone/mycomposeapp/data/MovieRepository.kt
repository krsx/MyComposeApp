package com.capstone.mycomposeapp.data

import android.util.Log
import com.capstone.mycomposeapp.model.FakeMovieDataSource
import com.capstone.mycomposeapp.model.FavoriteMovie
import com.capstone.mycomposeapp.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class MovieRepository {
    private val favoriteMovies = mutableListOf<FavoriteMovie>()

    init {
        if (favoriteMovies.isEmpty()) {
            FakeMovieDataSource.dummyMovieList.forEach {
                favoriteMovies.add(FavoriteMovie(it, false))
            }
        }
    }
    fun getAllMovies(): Flow<List<FavoriteMovie>> {
        return flowOf(favoriteMovies)
    }

    fun getAllFavoritesMovies(): Flow<List<FavoriteMovie>> {
        return flowOf(favoriteMovies.filter { it.isFavorite != false })
    }

    fun searchMovies(query: String): Flow<List<FavoriteMovie>> {
        return flowOf(favoriteMovies.filter {
            it.movie.title.contains(query, ignoreCase = true)
        })
    }

    fun getMovieDetail(id: Int): FavoriteMovie {
        return favoriteMovies.first {
            it.movie.id == id
        }
    }

    fun updateFavoriteMovieById(id: Int, isFavorite: Boolean): Flow<Boolean> {
        Log.e("CHECK", "Ini ${id} dan ${isFavorite}")
        val index = favoriteMovies.indexOfFirst { it.movie.id == id }
        val result = if (index >= 0) {
            val favoriteMovie = favoriteMovies[index]
            favoriteMovies[index] =
                favoriteMovie.copy(movie = favoriteMovie.movie, isFavorite = isFavorite)
            true
        } else {
            false
        }

        Log.e("TEST", favoriteMovies.first().toString())
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository = instance ?: synchronized(this) {
            MovieRepository().apply { instance = this }
        }
    }
}