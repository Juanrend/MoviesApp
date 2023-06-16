package com.example.moviesapp.navigation

sealed class AppScreens(val route:String){

    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object TopMoviesScreen:AppScreens("top_movies_screen")
    object MovieDetailsScreen:AppScreens("movie_details_screen")

}
