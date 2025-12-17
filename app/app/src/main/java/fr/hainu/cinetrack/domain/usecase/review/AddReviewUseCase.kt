package fr.hainu.cinetrack.domain.usecase.review

import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.repository.ReviewRepository

class AddReviewUseCase(private val repository: ReviewRepository) {
    suspend operator fun invoke(filmId: Int, rating: Int, comment: String): Result<ReviewModel> {
        return repository.createReview(filmId, rating, comment)
    }
}
