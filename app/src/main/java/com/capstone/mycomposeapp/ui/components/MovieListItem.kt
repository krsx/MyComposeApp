package com.capstone.mycomposeapp.ui.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.capstone.mycomposeapp.model.FavoriteMovie
import com.capstone.mycomposeapp.model.Movie
import com.capstone.mycomposeapp.ui.navigation.Screen
import com.capstone.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun MovieListItem(
    movie: FavoriteMovie,
    modifier: Modifier,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.small)
        .border(1.dp, Color.LightGray.copy(0.5f), MaterialTheme.shapes.small)
        ) {
        Column {
            AsyncImage(
                model = movie.movie.posterPath,
                contentDescription = movie.movie.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            MovieLIstItemDescription(
                movie = movie,
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState,
                onUpdateFavoriteMovie = onUpdateFavoriteMovie,
            )
        }
    }
}