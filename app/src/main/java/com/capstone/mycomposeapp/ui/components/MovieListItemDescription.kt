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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.mycomposeapp.model.FavoriteMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.round

@Composable
fun MovieLIstItemDescription(
    modifier: Modifier,
    movie: FavoriteMovie,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteMovie: (id: Int, isFavorite: Boolean) -> Unit,
    isDesc: Boolean = true,
) {

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

    var favorite by rememberSaveable { mutableStateOf(movie.isFavorite) }
    LaunchedEffect(movie.isFavorite) {
        favorite = movie.isFavorite
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = movie.movie.title, style = fontSize)
        Spacer(modifier = modifier.padding(4.dp))
        Text(

            text = movie.movie.genres,
            style = catFontSize.copy(
                color = MaterialTheme.colors.primary,
                fontSize = 14.sp
            )
        )
        Spacer(modifier = modifier.padding(4.dp))
        Row {
            val startCount = round(movie.movie.voteAverage / 2.0)
            repeat(startCount.toInt()) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = movie.movie.title,
                    tint = Color(0xFFFFCC00),
                    modifier = modifier.size(ratingIconSize.dp)
                )
            }
        }
        Spacer(modifier = modifier.padding(4.dp))
        Row(horizontalArrangement = Arrangement.End, modifier = modifier.fillMaxWidth()) {
            Icon(
                modifier = modifier
                    .size(btnFavSize.dp)
                    .clip(RoundedCornerShape(100))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        favorite = !favorite
                        onUpdateFavoriteMovie(movie.movie.id, favorite)
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "${movie.movie.title} ${if (!favorite) "removed from" else "added as"} favorite ",
                                actionLabel = "Dismiss",
                                duration = SnackbarDuration.Short
                            )
                        }
                    },
                imageVector = if (favorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                tint = if (favorite) Color.Red else MaterialTheme.colors.onSurface,
                contentDescription = movie.movie.title,
            )
        }

    }
}