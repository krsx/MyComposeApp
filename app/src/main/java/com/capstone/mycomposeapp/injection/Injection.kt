package com.capstone.mycomposeapp.injection

import com.capstone.mycomposeapp.data.MovieRepository

object Injection{
    fun provideRepository(): MovieRepository{
        return MovieRepository.getInstance()
    }
}