package com.movieland.data.source.network.services

import com.movieland.BuildConfig
import com.movieland.data.source.network.model.detail.DetailMovieResponse
import com.movieland.data.source.network.model.movieNowPlaying.NowPlayingResponse
import com.movieland.data.source.network.model.moviePopular.PopularMovieResponse
import com.movieland.data.source.network.model.movieTopRating.TopRatedResponse
import com.movieland.data.source.network.model.movieUpcoming.UpcomingMovieResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovielandApiService {
    @Headers("accept: application/json", "Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("3/movie/now_playing")
    suspend fun nowPlaying(
        @Query("page") page: Int
    ): NowPlayingResponse

    @Headers("accept: application/json", "Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("3/movie/popular")
    suspend fun popular(
        @Query("page") page: Int
    ): PopularMovieResponse

    @Headers("accept: application/json", "Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("3/movie/top_rated")
    suspend fun topRated(
        @Query("page") page: Int
    ): TopRatedResponse

    @Headers("accept: application/json", "Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("3/movie/upcoming")
    suspend fun upcoming(
        @Query("page") page: Int
    ): UpcomingMovieResponse


    @GET("3/movie/{id}?language=en-US")
    suspend fun getMovieDetail(
        @Path("id") id: String,
    ): DetailMovieResponse

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
