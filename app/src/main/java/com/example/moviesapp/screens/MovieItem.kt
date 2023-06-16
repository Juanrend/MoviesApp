package com.example.moviesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie


@Composable
fun MovieItem(movie: Movie, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .background(
                Color.Black.copy(alpha = 0.5f)
            )
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                builder = {
                    placeholder(R.drawable.img_empty) // Reemplaza con el recurso de imagen de placeholder correcto
                }
            ),
            contentDescription = movie.title,
            modifier = Modifier
                .height(200.dp)
                .width(150.dp)
                .align(Alignment.CenterHorizontally)
        )


        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 2.dp)
        )
        Text(
            text = "Lenguaje original: ${movie.original_language}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 2.dp, top = 4.dp)
        )
        Text(
            text = "Lanzamiento: ${movie.release_date}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 2.dp, top = 4.dp),
            color = Color.White

        )
        Text(
            text = "Voto promedio: ${movie.vote_average}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 2.dp, top = 4.dp),
            color = Color.White
        )

    }
}

@Preview(showBackground = true)
@Composable
fun MoviePreview() {

    val movie = Movie(
        id = 123,
        title = "Mario",
        overview = "This is a great movie.",
        popularity = "8.5",
        original_language = "English",
        original_title = "The Movie",
        release_date = "2022-01-01",
        vote_average = "9.2",
        poster_path = "poster.jpg",
        backdrop_path = "backdrop.jpg"
    )

    MovieItem(movie, onItemClick = {})

}