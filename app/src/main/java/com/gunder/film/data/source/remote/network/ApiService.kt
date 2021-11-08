package com.gunder.film.data.source.remote.network

import com.gunder.film.BuildConfig
import com.gunder.film.data.source.remote.response.DetailResponse
import com.gunder.film.data.source.remote.response.ResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("trending/movie/week")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ): Call<ResponseItem>

    @GET("movie/{movie_id}")
    fun detailMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ): Call<DetailResponse>

    @GET("trending/tv/week")
    fun getTv(
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ): Call<ResponseItem>


    @GET("tv/{tv_id}")
    fun detailTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TOKEN
    ): Call<DetailResponse>
}