package com.capstone.mycomposeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
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
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        AsyncImage(
            model = movie.movie.posterPath,
            contentDescription = movie.movie.title,
            modifier = Modifier.width(150.dp),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(12.dp))
        MovieLIstItemDescription(
            movie = movie,
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState,
            onUpdateFavoriteMovie = onUpdateFavoriteMovie,
            isDesc = false
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
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}