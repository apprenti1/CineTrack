package fr.hainu.cinetrack.data.remote

import fr.hainu.cinetrack.data.remote.models.MovieDto
import fr.hainu.cinetrack.data.remote.services.MovieApiService
import fr.hainu.cinetrack.network.RetrofitInstance

class MovieRemoteDataSource {
    private val api = RetrofitInstance.apiTmdb

    private val movieService = api.create(MovieApiService::class.java)

    suspend fun fetchMovie() = movieService.getAllMovie()
    suspend fun fetchUserMovieList() = movieService.getUserMovie()

    suspend fun fetchMovieById(id: Int) = movieService.getMovieById(id)
    suspend fun postMovie(list: MovieDto) = movieService.createMovie(list)
    
    suspend fun updateMovie(id: Int, list: MovieDto) = movieService.updateMovie(id, list)
    suspend fun removeMovie(id: Int) = movieService.deleteMovie(id)
}