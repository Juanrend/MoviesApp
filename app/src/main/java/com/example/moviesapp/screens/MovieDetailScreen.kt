package com.example.moviesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie

@Composable
fun MovieDetailsScreen(navController: NavController) {

    val arguments = navController.currentBackStackEntry?.arguments
    val title = arguments?.getString("title") ?: ""
    val overview = arguments?.getString("overview") ?: ""
    val backdrop_path = arguments?.getString("backdrop_path") ?: ""


    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {

        MovieDetails(title, overview, backdrop_path, Modifier.align(Alignment.CenterHorizontally))
        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .align(Alignment.End)


        ) {
            Text(text = "Volver")
        }
    }
}

@Composable
fun MovieDetails(title: String, overview: String, backdrop_path: String, modifier: Modifier) {


    Column(
        modifier = modifier.padding(16.dp).background(Color.Black)
    ) {


        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(bottom= 16.dp)
                .fillMaxWidth()

        )
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500/${backdrop_path}",
                builder = {
                    placeholder(R.drawable.img_empty)
                }
            ),
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp)

        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = overview,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DetailPreview() {

    val title = "Mario"
    val overview =
        "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum"
    val backdrop_path = "backdrop.jpg"
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
    MovieDetails(title, overview, backdrop_path, modifier = Modifier.fillMaxSize())
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.End),
            colors = ButtonDefaults.buttonColors(Color.Red)


        ) {
            Text(text = "Volver", color = Color.White)
        }
    }
}