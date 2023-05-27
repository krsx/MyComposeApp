package com.capstone.mycomposeapp.ui.components

import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
    isDesc: Boolean = true
) {
    var padding = 14
    var fontSize = MaterialTheme.typography.h5.copy(fontSize = 26.sp)
    var ratingIconSize = 24
    var btnFavSize = 32
    var catFontSize = MaterialTheme.typography.h6.copy(fontSize = 20.sp)

    if (isDesc) {
        fontSize = MaterialTheme.typography.h4.copy(fontSize = 20.sp)
        ratingIconSize = 20
        btnFavSize = 24
        catFontSize = MaterialTheme.typography.h5.copy(fontSize = 16.sp)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = movie.movie.title, style = fontSize)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(

            text = movie.movie.genres,
            style = catFontSize.copy(
                color = MaterialTheme.colors.primary,
                        fontSize = 14.sp
            )
        )
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
                        onUpdateFavoriteMovie(movie.movie.id, !movie.isFavorite)
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "${movie.movie.title} ${if (movie.isFavorite) "removed from" else "added as"} favorite ",
                                actionLabel = "Dismiss",
                                duration = SnackbarDuration.Short
                            )
                        }
                    },
            )
        }

    }
}