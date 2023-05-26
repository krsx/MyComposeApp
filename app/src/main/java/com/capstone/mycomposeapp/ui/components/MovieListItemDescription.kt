package com.capstone.mycomposeapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.capstone.mycomposeapp.model.FavoriteMovie
import com.capstone.mycomposeapp.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.round

@Composable
fun MovieLIstItemDescription(
    movie: FavoriteMovie,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    isSmall: Boolean = false,

) {
    var padding = 14
    var fontSize = MaterialTheme.typography.h6
    var ratingIconSize = 18
    var btnFavSize = 32

    if (isSmall) {
        padding = 16
        fontSize = MaterialTheme.typography.subtitle1
        ratingIconSize = 14
        btnFavSize = 24
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = movie.movie.title, style = fontSize)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = movie.movie.genres, style = TextStyle(color = MaterialTheme.colors.primary))
        Spacer(modifier = Modifier.padding(4.dp))
        Row {
            val startCount = round(movie.movie.voteAverage / 2.0)
            repeat(startCount.toInt()) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = movie.movie.title,
                    tint = Color(0xFFFFCC00),
                    modifier = Modifier.size(ratingIconSize.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = if (movie.isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                tint = if (movie.isFavorite) Color.Red else MaterialTheme.colors.onSurface,
                contentDescription = movie.movie.title,
                modifier = Modifier
                    .size(btnFavSize.dp)
                    .clip(RoundedCornerShape(100))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
//                        onUpdateFavoriteMovie(movie.movie.id, !movie.isFavorite)
//                        coroutineScope.launch {
//                            scaffoldState.snackbarHostState.showSnackbar(
//                                message = "${movie.movie.title} ${if (movie.isFavorite) "removed from" else "added as"} favorite ",
//                                actionLabel = "Dismiss",
//                                duration = SnackbarDuration.Short
//                            )
//                        }
                    },
            )
        }

    }
}