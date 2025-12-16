package fr.hainu.cinetrack.ui.viewmodels

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.mock.MockMovieRepository
import fr.hainu.cinetrack.ui.mock.MockMovieRepository.MovieType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {

    private val repository = mockk<MockMovieRepository>()
    private lateinit var viewModel: MoviesViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Dispatchers::class)
        every { Dispatchers.IO } returns testDispatcher

        // Default mock behavior for init block
        coEvery { repository.getMovies(any()) } returns emptyList()
        
        viewModel = MoviesViewModel(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchTrendingMoviesWeek updates trendingMoviesWeek state`() = runTest(testDispatcher) {
        // Given
        val movies = listOf(MovieModel(id = 1, title = "Movie 1", posterUrl = "", year = "", rating = 0.0, genres = "", synopsis = "", backdropUrl = "", ratingCoef = 0))
        coEvery { repository.getMovies(MovieType.TREND_WEEK) } returns movies

        // When
        viewModel.fetchTrendingMoviesWeek()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(movies, viewModel.trendingMoviesWeek.value)
        coVerify { repository.getMovies(MovieType.TREND_WEEK) }
    }

    @Test
    fun `fetchTrendingMovies updates trendingMovies state`() = runTest(testDispatcher) {
        val movies = listOf(MovieModel(id = 2, title = "Movie 2", posterUrl = "", year = "", rating = 0.0, genres = "", synopsis = "", backdropUrl = "", ratingCoef = 0))
        coEvery { repository.getMovies(MovieType.TREND) } returns movies

        viewModel.fetchTrendingMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(movies, viewModel.trendingMovies.value)
    }

    @Test
    fun `fetchRecentMovies updates recentMovies state`() = runTest(testDispatcher) {
        val movies = listOf(MovieModel(id = 3, title = "Movie 3", posterUrl = "", year = "", rating = 0.0, genres = "", synopsis = "", backdropUrl = "", ratingCoef = 0))
        coEvery { repository.getMovies(MovieType.RECENT) } returns movies

        viewModel.fetchRecentMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(movies, viewModel.recentMovies.value)
    }
    
    @Test
    fun `loadMovieDetails updates currentMovieDetails and fetches details if not detailed`() = runTest(testDispatcher) {
        val movie = MovieModel(id = 4, title = "Movie 4", posterUrl = "", year = "", rating = 0.0, genres = "", synopsis = "", backdropUrl = "", ratingCoef = 0, isDetailed = false)
        coEvery { repository.getMovieDetails(movie) } returns Unit

        viewModel.loadMovieDetails(movie)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(movie, viewModel.currentMovieDetails.value)
        coVerify { repository.getMovieDetails(movie) }
    }
}
