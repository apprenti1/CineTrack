package fr.hainu.cinetrack.domain.usecase.review

import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.repository.ReviewRepository

class GetAllUserReviewUseCase(private val repository: ReviewRepository) {
    suspend operator fun invoke(id : Int): List<ReviewModel> = repository.fetchFromUser(id)
}