package fr.hainu.cinetrack.domain.repository

import fr.hainu.cinetrack.data.remote.models.ReviewDto
import fr.hainu.cinetrack.domain.models.ReviewModel

interface ReviewRepository {
    suspend fun fetchAll(): List<ReviewModel>
    suspend fun fetchFromUser(id: Int): List<ReviewModel>
    suspend fun fetchFromMovie(id: Int): List<ReviewModel>
    suspend fun fetchById(id: Int): ReviewModel?
    suspend fun addToMovie(review: ReviewModel)
    suspend fun remove(review: ReviewModel): Boolean
    suspend fun update(review: ReviewModel)
}