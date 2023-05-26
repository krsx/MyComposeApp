package com.capstone.mycomposeapp.data

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

    fun searchHeroes(query: String): List<Movie> {
        return FakeMovieDataSource.dummyMovieList.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    fun getMovieDetail(id: Int): FavoriteMovie {
        return favoriteMovies.first {
            it.movie.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository = instance ?: synchronized(this) {
            MovieRepository().apply { instance = this }
        }
    }
}