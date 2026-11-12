package com.example.buscadordepelliculas.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    //  Búsqueda por nombre (título)
    @GET("/")
    suspend fun getMoviesList(
        @Query("s") searchTerm: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MovieResponse

    //  Búsqueda por ID
    @GET("/")
    suspend fun getMovieById(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = API_KEY
    ): Movie

    companion object{
        private const val API_KEY = "cba37da2"

        fun getInstance(): MovieApi{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieApi::class.java)
        }
    }
}