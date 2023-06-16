package com.example.moviesapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
@SerializedName("results") val results: List<Movie>)
