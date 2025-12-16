package fr.hainu.cinetrack.domain.usecase.review

import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.repository.ReviewRepository

class RemoveReviewUseCase(private val repository: ReviewRepository) {
    suspend operator fun invoke(review : ReviewModel): Boolean = repository.remove(review)
}