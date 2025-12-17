package fr.hainu.cinetrack.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.usecase.review.ReviewUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReviewViewModel(private val reviewUseCase: ReviewUseCase) : ViewModel() {

    private val _movieReviews = MutableStateFlow<List<ReviewModel>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    val movieReviews = _movieReviews.asStateFlow()
    private val _reviewSubmitted = MutableStateFlow(false)
    //val isLoading = _isLoading.asStateFlow()
    //val errorMessage = _errorMessage.asStateFlow()
    val reviewSubmitted = _reviewSubmitted.asStateFlow()

    fun addReview(filmId: Int, rating: Int, comment: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                _reviewSubmitted.value = false
                val result = reviewUseCase.addReview(filmId, rating, comment)

                result.onSuccess { review ->
                    _reviewSubmitted.value = true
                    loadMovieReviews(filmId)
                }.onFailure { error ->
                    _errorMessage.value = "erreur ajout commentaire : ${error.message}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMovieReviews(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                // Log.d("ReviewViewModel", "Loading reviews for film $filmId")

                val result = reviewUseCase.getMovieReviews(filmId)

                result.onSuccess { reviews ->
                    // Log.d("ReviewViewModel", "Loaded ${reviews.size} reviews")
                    _movieReviews.value = reviews
                }.onFailure { error ->
                    Log.e("ReviewViewModel", "Error loading reviews", error)
                    _errorMessage.value = "Erreur lors du chargement des commentaires : ${error.message}"
                }
            } catch (e: Exception) {
                Log.e("ReviewViewModel", "Exception loading reviews", e)
                _errorMessage.value = "Erreur inattendue : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun resetReviewSubmitted() {
        _reviewSubmitted.value = false
    }
}
