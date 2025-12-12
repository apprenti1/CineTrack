package fr.hainu.cinetrack.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.mock.MockMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val repository = MockMovieRepository()
    val trendingMoviesWeek = MutableStateFlow<List<MovieModel>>(emptyList())
    val trendingMovies = MutableStateFlow<List<MovieModel>>(emptyList())
    val recentMovies = MutableStateFlow<List<MovieModel>>(emptyList())



    init {
        fetchTrendingMoviesWeek()
        fetchTrendingMovies()
        fetchRecentMovies()
    }

    fun fetchTrendingMoviesWeek() {
        viewModelScope.launch(Dispatchers.IO) {
            trendingMoviesWeek.value = repository.getTrendingMoviesWeek()
        }
    }

    fun fetchTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            trendingMovies.value = repository.getTrendingMoviesWeek()
        }
    }

    fun fetchRecentMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            recentMovies.value = repository.getTrendingMoviesWeek()
        }
    }
}