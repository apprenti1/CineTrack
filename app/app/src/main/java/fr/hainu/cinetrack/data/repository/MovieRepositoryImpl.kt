package fr.hainu.cinetrack.data.repository

import fr.hainu.cinetrack.data.mapper.mapMovieDtoToMovieModel
import fr.hainu.cinetrack.data.mapper.mapMovieModelToMovieDto
import fr.hainu.cinetrack.data.remote.MovieRemoteDataSource
import fr.hainu.cinetrack.domain.repository.MovieRepository
import fr.hainu.cinetrack.domain.models.MovieModel

class MovieRepositoryImpl(
    val remote: MovieRemoteDataSource = MovieRemoteDataSource()
) : MovieRepository {


    override suspend fun fetchAll(): List<MovieModel> {
        val dto = remote.fetchMovies()
        return mapMovieDtoToMovieModel(dto)
    }

    override suspend fun fetchFromUser(): List<MovieModel> {
        val dto = remote.fetchUserMovieList()
        return mapMovieDtoToMovieModel(dto)
    }

    override suspend fun fetchById(id: Int): MovieModel? {
        val dto = remote.fetchMovieById(id)
        return mapMovieDtoToMovieModel(dto)
    }

    override suspend fun add(movie: MovieModel) {
        val dto = mapMovieModelToMovieDto(movie)
        remote.postMovie(dto)
    }

    override suspend fun remove(movie: MovieModel): Boolean {
        remote.removeMovie(movie.id)
        return true
    }

    override suspend fun update(movie: MovieModel) {
        val dto = mapMovieModelToMovieDto(movie)
        remote.updateMovie(dto.id, dto)
    }
}
