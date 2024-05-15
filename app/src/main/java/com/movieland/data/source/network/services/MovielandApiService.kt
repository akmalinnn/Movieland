package com.movieland.data.source.network.services

import com.movieland.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface MovielandApiService {
    @Headers("accept: application/json", "Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("3/movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlaying(): MovieResponse //Bikin model

    @GET("3/movie/popular?language=en-US&page=1")
    suspend fun getPopular(): MovieResponse

    @GET("3/movie/top_rated?language=en-US&page=1")
    suspend fun getTopRated(): MovieResponse

    @GET("3/movie/upcoming?language=en-US&page=1")
    suspend fun getUpcoming(): MovieResponse

    @GET("3/movie/{id}?language=en-US")
    suspend fun getMovieDetail(
        @Path("id") id: String,
    ): MovieResponse

    companion object {
        @JvmStatic
        operator fun invoke(): MovielandApiService {
            val okHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(MovielandApiService::class.java)
        }
    }
}
