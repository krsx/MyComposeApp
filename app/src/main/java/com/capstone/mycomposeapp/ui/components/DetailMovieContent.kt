package com.capstone.mycomposeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.capstone.mycomposeapp.model.FavoriteMovie

@Composable
fun DetailMovieContent(
    movie: FavoriteMovie, scaffoldState: ScaffoldState,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = movie.movie.posterPath,
            contentDescription = movie.movie.title,
            modifier = Modifier.width(200.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        MovieLIstItemDescription(
            movie = movie,
            coroutineScope =coroutineScope ,
            scaffoldState = scaffoldState,
            onUpdateFavoriteMovie = onUpdateFavoriteMovie
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Rating: ${movie.movie.voteAverage}/${movie.movie.voteCount}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Release: ${movie.movie.releaseDate}",
                    style = MaterialTheme.typography.caption
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = movie.movie.overview,
                style = MaterialTheme.typography.body1
            )
        }
    }
}