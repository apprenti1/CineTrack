package fr.hainu.cinetrack.data.repository

import fr.hainu.cinetrack.data.mapper.mapTmdbMovieDetailsDtoToModel
import fr.hainu.cinetrack.data.mapper.mapTmdbMovieDetailsDtosToModels
import fr.hainu.cinetrack.data.mapper.mapTmdbMovieDtosToModels
import fr.hainu.cinetrack.data.remote.MovieRemoteDataSource
import fr.hainu.cinetrack.domain.repository.MovieRepository
import fr.hainu.cinetrack.domain.models.MovieModel

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource = MovieRemoteDataSource()
) : MovieRepository {

    override suspend fun getTrendingWeek(): List<MovieModel> {
        val dtos = remoteDataSource.fetchTrendingWeek()
        return mapTmdbMovieDtosToModels(dtos)
    }

    override suspend fun getPopular(page: Int): List<MovieModel> {
        val dtos = remoteDataSource.fetchPopular(page)
        return mapTmdbMovieDtosToModels(dtos)
    }

    override suspend fun getNowPlaying(page: Int): List<MovieModel> {
        val dtos = remoteDataSource.fetchNowPlaying(page)
        return mapTmdbMovieDtosToModels(dtos)
    }

    override suspend fun searchMovies(query: String, page: Int): List<MovieModel> {
        val dtos = remoteDataSource.searchMovies(query, page)
        return mapTmdbMovieDtosToModels(dtos)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieModel? {
        return try {
            val dto = remoteDataSource.fetchMovieDetails(movieId)
            mapTmdbMovieDetailsDtoToModel(dto)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getMoviesByIds(movieIds: List<Int>): List<MovieModel> {
        val dtos = remoteDataSource.fetchMoviesByIds(movieIds)
        return mapTmdbMovieDetailsDtosToModels(dtos)
    }

    override suspend fun getSimilarMovies(movieId: Int, page: Int): List<MovieModel> {
        val dtos = remoteDataSource.fetchSimilarMovies(movieId, page)
        return mapTmdbMovieDtosToModels(dtos)
    }
}
