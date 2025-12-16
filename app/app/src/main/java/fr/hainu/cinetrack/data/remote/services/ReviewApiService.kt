package fr.hainu.cinetrack.data.remote.services


import fr.hainu.cinetrack.data.remote.models.ReviewDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewApiService {
    // https://my-json-server.typicode.com/RamzyK//demo/notes

    //GET REQUEST
    @GET("reviews")
    suspend fun getAllReview(): List<ReviewDto>

    @GET("reviews/user/{userId}")
    suspend fun getUserReview(
        @Path("userId") id: Int
    ): List<ReviewDto>

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReview(
        @Path("movieId") id: Int
    ): List<ReviewDto>

    @GET("reviews/{id}")
    suspend fun getReviewById(
        @Path("id") id: Int
    ): ReviewDto

    //POST REQUEST

    @POST("reviews/movie/{movieId}")
    suspend fun createMovieReview(
        @Path("movieId") id: Int,
        @Body list: ReviewDto
    ): ReviewDto

    //PUT REQUEST

    @PUT("reviews/{id}")
    suspend fun updateReview(
        @Path("id") id: Int,
        @Body note: ReviewDto
    ):ReviewDto

    //DELETE REQUEST

    @DELETE("reviews/{id}")
    suspend fun deleteReview(
        @Path("id") id: Int
    ): Response<Unit>

}