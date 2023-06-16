package com.example.moviesapp.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.network.MovieApiService
import com.example.moviesapp.navigation.AppScreens
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun TopMoviesScreen(navController: NavController) {


    TopMovies(navController)


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMovies(navController: NavController) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/") // URL que se está utilizando para la data
        .addConverterFactory(GsonConverterFactory.create()) // Utiliza Gson como convertidor JSON
        .build()

    val movieApiService = retrofit.create(MovieApiService::class.java)

    var topRatedMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var popularMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }
    var filteredMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            val apiKey = "d22413a6de096b7fe139b54a0c90b97d" // API key
            isLoading = true
            val topRatedResponse = movieApiService.getTopRatedMovies(apiKey)
            val popularResponse = movieApiService.getPopularMovies(apiKey)

            if (topRatedResponse.isSuccessful && popularResponse.isSuccessful) {
                topRatedMovies = topRatedResponse.body()?.results ?: emptyList()
                popularMovies = popularResponse.body()?.results ?: emptyList()
            } else {
                // Manejar el error de la solicitud API
            }
            isLoading = false
        } catch (e: Exception) {
            isLoading = false // Manejar el error de conexión o cualquier otra excepción
        }
    }

    LaunchedEffect(searchQuery) {
        try {
            if (searchQuery.isNotEmpty()) {
                val apiKey = "d22413a6de096b7fe139b54a0c90b97d"
                isLoading = true
                val response = movieApiService.searchMovies(apiKey, searchQuery)
                if (response.isSuccessful) {
                    filteredMovies = response.body()?.results ?: emptyList()
                } else {
                    filteredMovies = emptyList()
                }
                isLoading = false
            } else {
                filteredMovies = emptyList()
            }
        } catch (e: Exception) {
            // Manejar el error de conexión o cualquier otra excepción
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(16.dp)
        ) {
            Surface(
                color = Color.Gray,
                shadowElevation = 4.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { /* Acción al hacer clic en el icono de búsqueda */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = Color.White
                        )
                    }
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { query -> searchQuery = query },
                        placeholder = { Text(text = "Buscar", color = Color.White) },
                        textStyle = TextStyle(color = Color.White),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color.White,
                            cursorColor = Color.White,
                            focusedLeadingIconColor = Color.White,
                            unfocusedLeadingIconColor = Color.White,
                            containerColor = Color.Gray,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                }
            }
        }

        if (searchQuery.isEmpty()) {
            Text(
                text = "Películas más valoradas",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 20.dp, top = 16.dp, start = 16.dp, end = 16.dp),
                color = Color.White
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(topRatedMovies) { movie ->
                    MovieItem(movie) {
                        navController.navigate(
                            "movie_details/${Uri.encode(movie.title)}/${Uri.encode(movie.overview)}/${Uri.encode(
                                movie.backdrop_path
                            )}"
                        )
                    }
                }
            }

            Text(
                text = "Películas más populares",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp, top = 16.dp, start = 16.dp, end = 16.dp),
                color = Color.White
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(popularMovies) { movie ->
                        MovieItem(movie) {
                            navController.navigate(
                                "movie_details/${Uri.encode(movie.title)}/${Uri.encode(movie.overview)}/${Uri.encode(
                                    movie.backdrop_path
                                )}"
                            )
                        }
                    }
                }
            }
        } else {
            if (filteredMovies.isNotEmpty()) {
                Text(
                    text = "Resultados de búsqueda",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 20.dp, top = 16.dp, start = 16.dp, end = 16.dp),
                    color = Color.White
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(filteredMovies) { movie ->
                        MovieItem(movie) {
                            navController.navigate(
                                "movie_details/${Uri.encode(movie.title)}/${Uri.encode(movie.overview)}/${Uri.encode(
                                    movie.backdrop_path
                                )}"
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = "No se encontraron resultados",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 20.dp, top = 16.dp, start = 16.dp, end = 16.dp),
                    color = Color.White
                )
            }
        }
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopMoviesScreenPreview() {
    val context = LocalContext.current
    TopMovies(
        navController = NavController(
            context
        )
    )


}