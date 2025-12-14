package fr.hainu.cinetrack.data.repository


import fr.hainu.cinetrack.data.mapper.mapReviewDtoToReviewModel
import fr.hainu.cinetrack.data.mapper.mapReviewModelToReviewDto
import fr.hainu.cinetrack.data.remote.ReviewRemoteDataSource
import fr.hainu.cinetrack.domain.repository.ReviewRepository
import fr.hainu.cinetrack.domain.models.ReviewModel

class ReviewRepositoryImpl(
    val remote: ReviewRemoteDataSource = ReviewRemoteDataSource()
) : ReviewRepository {


    override suspend fun fetchAll(): List<ReviewModel> {
        val dto = remote.fetchReviews()
        return mapReviewDtoToReviewModel(dto)
    }

    override suspend fun fetchFromUser(id: Int): List<ReviewModel> {
        val dto = remote.fetchUserReviewById(id)
        return mapReviewDtoToReviewModel(dto)
    }

    override suspend fun fetchById(id: Int): ReviewModel? {
        val dto = remote.fetchReviewById(id)
        return mapReviewDtoToReviewModel(dto)
    }

    override suspend fun fetchFromMovie(id: Int): List<ReviewModel> {
        val dto = remote.fetchMovieReviewList(id)
        return mapReviewDtoToReviewModel(dto)
    }

    override suspend fun addToMovie(review: ReviewModel) {
        val dto = mapReviewModelToReviewDto(review)
        remote.postReview(dto.id, dto)
    }

    override suspend fun remove(review: ReviewModel): Boolean {
        remote.removeReview(review.id)
        return true
    }

    override suspend fun update(review: ReviewModel) {
        val dto = mapReviewModelToReviewDto(review)
        remote.updateReview(dto.id, dto)
    }
}