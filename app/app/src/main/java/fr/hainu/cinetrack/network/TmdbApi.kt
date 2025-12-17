package fr.hainu.cinetrack.network

import fr.hainu.cinetrack.data.remote.models.TmdbMovieDetailsDto
import fr.hainu.cinetrack.data.remote.models.TmdbMovieListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    // Films tendance de la semaine
    @GET("trending/movie/week")
    suspend fun getTrendingWeek(): TmdbMovieListResponseDto

    // Films populaires
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int = 1
    ): TmdbMovieListResponseDto

    // Films récents (en cours de diffusion)
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int = 1
    ): TmdbMovieListResponseDto

    // Recherche de films
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): TmdbMovieListResponseDto

    // Détails d'un film avec cast, crew et vidéos
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String = "videos,credits"
    ): TmdbMovieDetailsDto

    // Films similaires
    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1
    ): TmdbMovieListResponseDto
}