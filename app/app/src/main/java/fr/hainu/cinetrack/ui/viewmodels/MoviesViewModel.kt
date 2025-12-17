package fr.hainu.cinetrack.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieUseCases: MovieUseCase) : ViewModel() {

    // Films tendance de la semaine
    private val _trendingMoviesWeek = MutableStateFlow<List<MovieModel>>(emptyList())
    val trendingMoviesWeek = _trendingMoviesWeek.asStateFlow()

    // Films populaires
    private val _trendingMovies = MutableStateFlow<List<MovieModel>>(emptyList())
    val trendingMovies = _trendingMovies.asStateFlow()

    // Films récents
    private val _recentMovies = MutableStateFlow<List<MovieModel>>(emptyList())
    val recentMovies = _recentMovies.asStateFlow()

    // Films de recherche
    private val _moviesWithSearch = MutableStateFlow<List<MovieModel>>(emptyList())
    val moviesWithSearch = _moviesWithSearch.asStateFlow()

    // Détails du film courant
    private val _currentMovieDetails = MutableStateFlow<MovieModel?>(null)
    val currentMovieDetails = _currentMovieDetails.asStateFlow()

    // Films récupérés par leurs IDs (pour watchlist, watched, favorites)
    private val _moviesByIds = MutableStateFlow<List<MovieModel>>(emptyList())
    val moviesByIds = _moviesByIds.asStateFlow()

    // État de chargement
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Message d'erreur
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        fetchTrendingMoviesWeek()
        fetchTrendingMovies()
        fetchRecentMovies()
    }

    fun fetchTrendingMoviesWeek() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val movies = movieUseCases.getTrendingWeek()
                _trendingMoviesWeek.value = movies
            } catch (e: Exception) {
                _errorMessage.value = "Erreur lors du chargement des films tendance : ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val movies = movieUseCases.getPopular()
                _trendingMovies.value = movies
            } catch (e: Exception) {
                _errorMessage.value = "Erreur lors du chargement des films populaires : ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchRecentMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val movies = movieUseCases.getNowPlaying()
                _recentMovies.value = movies
            } catch (e: Exception) {
                _errorMessage.value = "Erreur lors du chargement des films récents : ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchMoviesBySearch(query: String) {
        if (query.isBlank()) {
            _moviesWithSearch.value = emptyList()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val movies = movieUseCases.searchMovies(query)
                _moviesWithSearch.value = movies
            } catch (e: Exception) {
                _errorMessage.value = "Erreur lors de la recherche : ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMovieDetails(movie: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                if (movie.isDetailed) {
                    // Le film a déjà les détails
                    _currentMovieDetails.value = movie
                } else {
                    // Charger les détails depuis l'API
                    val detailedMovie = movieUseCases.getMovieDetails(movie.id)

                    // Charger les films similaires
                    val similarMovies = try {
                        movieUseCases.getSimilarMovies(movie.id).take(3)
                    } catch (e: Exception) {
                        emptyList()
                    }

                    _currentMovieDetails.value = detailedMovie?.copy(similarMovies = similarMovies) ?: movie
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur lors du chargement des détails : ${e.message}"
                e.printStackTrace()
                _currentMovieDetails.value = movie
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMoviesByIds(movieIds: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val movies = movieUseCases.getMoviesByIds(movieIds)
                _moviesByIds.value = movies
            } catch (e: Exception) {
                _errorMessage.value = "Erreur lors du chargement des films : ${e.message}"
                e.printStackTrace()
                _moviesByIds.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}