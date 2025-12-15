package fr.hainu.cinetrack.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.mock.MockMovieRepository
import fr.hainu.cinetrack.ui.mock.MockMovieRepository.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query

class MoviesViewModel : ViewModel() {

    private val repository = MockMovieRepository()
    val trendingMoviesWeek = MutableStateFlow<List<MovieModel>>(emptyList())
    val trendingMovies = MutableStateFlow<List<MovieModel>>(emptyList())
    val recentMovies = MutableStateFlow<List<MovieModel>>(emptyList())

    val moviesWithSearch = MutableStateFlow<List<MovieModel>>(emptyList())

    private val _currentMovieDetails = MutableStateFlow<MovieModel?>(null)
    val currentMovieDetails = _currentMovieDetails.asStateFlow()



    init {
        fetchTrendingMoviesWeek()
        fetchTrendingMovies()
        fetchRecentMovies()
    }

    fun fetchTrendingMoviesWeek() {
        viewModelScope.launch(Dispatchers.IO) {
            trendingMoviesWeek.value = repository.getMovies(MovieType.TREND_WEEK)
        }
    }

    fun fetchTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            trendingMovies.value = repository.getMovies(MovieType.TREND)
        }
    }

    fun fetchRecentMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            recentMovies.value = repository.getMovies(MovieType.RECENT)

        }
    }

    fun fetchMoviesBySearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesWithSearch.value = repository.getMoviesWithSearch(query)
        }
    }

    fun loadMovieDetails(movie: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentMovieDetails.value = movie
            if (!movie.isDetailed) {
                repository.getMovieDetails(movie)
                // Trigger StateFlow update by reassigning the same object
                _currentMovieDetails.value = _currentMovieDetails.value
            }
        }
    }
}