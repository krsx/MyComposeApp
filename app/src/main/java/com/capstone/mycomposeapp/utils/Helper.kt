package com.capstone.mycomposeapp.utils

object Helper {
    fun String.createImageLink(): String = "https://image.tmdb.org/t/p/w185$this"
}