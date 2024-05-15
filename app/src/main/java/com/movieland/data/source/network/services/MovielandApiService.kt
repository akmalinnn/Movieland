package com.movieland.data.source.network.services

import com.movieland.BuildConfig
import com.movieland.data.source.network.model.MovieNowPlayingResponse
import com.movieland.data.source.network.model.MoviePopularResponse
import com.movieland.data.source.network.model.MovieTopRatedResponse
import com.movieland.data.source.network.model.MovieUpComingResponse
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
    @GET("3/movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlaying(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_release_type") withReleaseType: String = "2|3",
        @Query("release_date.gte") minDate: String,
        @Query("release_date.lte") maxDate: String,
    ): MovieNowPlayingResponse

    @GET("3/movie/popular?language=en-US&page=1")
    suspend fun getPopular(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): MoviePopularResponse

    @GET("3/movie/top_rated?language=en-US&page=1")
    suspend fun getTopRated(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "vote_average.desc",
        @Query("without_genres") withoutGenres: String = "99,10755",
        @Query("vote_count.gte") voteCountGte: Int = 200,
    ): MovieTopRatedResponse

    @GET("3/movie/upcoming?language=en-US&page=1")
    suspend fun getUpcoming(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_release_type") withReleaseType: String = "2|3",
        @Query("release_date.gte") minDate: String,
        @Query("release_date.lte") maxDate: String,
    ): MovieUpComingResponse

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
