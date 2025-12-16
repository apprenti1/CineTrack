package fr.hainu.cinetrack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel( private val useCases: MovieUseCase
): ViewModel() {

    // ============================================================
    // ==================== REMOTE DATA ============================
    // ============================================================
    private val _remoteMovie = MutableStateFlow<List<MovieModel>>(emptyList())
    val remoteMovie = _remoteMovie.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        fetchMovieFromRepo()
    }

    fun fetchMovieFromRepo() {
        viewModelScope.launch {
            try {
                _remoteMovie.value = useCases.getAllMovie()
            } catch (e: Exception) {
                _error.value = "Erreur de chargement des données. Error: ${e.message}"
            }
        }
    }
    fun getMovieById(id: Int): MovieModel? = _remoteMovie.value.find { it.id == id }
}