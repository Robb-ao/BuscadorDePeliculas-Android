package com.example.buscadordepelliculas.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search") val movies: List<Movie>
)

data class Movie(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Runtime") val runtime: String?,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("Director") val director: String?,
    @SerializedName("Country") val country: String?,
    @SerializedName("Plot") val plot: String?
)