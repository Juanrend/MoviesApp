package com.example.moviesapp.navigation

import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.screens.LoginScreen
import com.example.moviesapp.screens.MovieDetailsScreen
import com.example.moviesapp.screens.SplashScreen
import com.example.moviesapp.screens.TopMoviesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val movie = Movie(
        id = 123,
        title = "Mario",
        overview = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum",
        popularity = "8.5",
        original_language = "English",
        original_title = "The Movie",
        release_date = "2022-01-01",
        vote_average = "9.2",
        poster_path = "poster.jpg",
        backdrop_path = "backdrop.jpg"
    )
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.route){
            LoginScreen(navController,onLoginSuccess =  {})
        }
        composable(AppScreens.TopMoviesScreen.route){
            TopMoviesScreen(navController)
        }




        composable(
            route= "movie_details/{title}/{overview}/{backdrop_path}",
            arguments= listOf(
                navArgument("title"){ type = NavType.StringType },
                navArgument("overview") { type = NavType.StringType },
                navArgument("backdrop_path") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val title = backStackEntry.arguments?.getString("title")
            val overview = backStackEntry.arguments?.getString("overview")
            val backdrop_path = backStackEntry.arguments?.getString("backdrop_path")

            requireNotNull(title)
            requireNotNull(overview)
            requireNotNull(backdrop_path)
            MovieDetailsScreen(navController = navController)

        }

      /*  composable(AppScreens.MovieDetailsScreen.route){
                MovieDetailsScreen(movie, navController)

        }*/

    }
}


