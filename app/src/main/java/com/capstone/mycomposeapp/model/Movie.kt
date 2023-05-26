package com.capstone.mycomposeapp.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: String,
    val posterPath: String,
)
