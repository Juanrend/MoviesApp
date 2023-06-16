package com.example.moviesapp.data.network

import com.example.moviesapp.data.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): Response<MovieListResponse>

    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieListResponse>

    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<MovieListResponse>
}
