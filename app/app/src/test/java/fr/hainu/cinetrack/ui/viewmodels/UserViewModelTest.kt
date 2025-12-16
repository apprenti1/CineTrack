package fr.hainu.cinetrack.ui.viewmodels

import android.app.Application
import fr.hainu.cinetrack.data.local.SecurePreferencesManager
import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.ui.mock.MockUserRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    private val application = mockk<Application>()
    private val repository = mockk<MockUserRepository>()
    private val securePrefs = mockk<SecurePreferencesManager>(relaxed = true)
    private lateinit var viewModel: UserViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Dispatchers::class)
        every { Dispatchers.IO } returns testDispatcher

        // Default stubs for init block
        every { securePrefs.hasCompletedOnboarding() } returns false
        every { securePrefs.isLoggedIn() } returns false

        viewModel = UserViewModel(application, repository, securePrefs)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun `login success updates state and saves token`() = runTest(testDispatcher) {
        // Given
        val pseudo = "test"
        val password = "pass"
        val userResponse = MockUserRepository.UserResponse(
            id = "1", pseudo = pseudo, email = "test@test.com", 
            watchlist = emptyList(), likes = emptyList(), watched = emptyList(), 
            createdAt = "", updatedAt = ""
        )
        val expectedUser = UserModel(
            id = "1", pseudo = pseudo, email = "test@test.com", password = "",
            watchlist = emptyList(), likes = emptyList(), watched = emptyList(), 
            createdAt = "", updatedAt = ""
        )
        val authResponse = MockUserRepository.AuthResponse(accessToken = "token", user = userResponse)
        coEvery { repository.login(pseudo, password) } returns Result.success(authResponse)

        // When
        viewModel.login(pseudo, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.isLoggedIn.value)
        assertEquals(expectedUser, viewModel.currentUser.value)
        verify { securePrefs.saveAuthToken("token") }
        verify { securePrefs.saveUserId("1") }
    }

    @Test
    fun `login failure updates error message`() = runTest(testDispatcher) {
        // Given
        val errorMsg = "Credentials incorrect"
        coEvery { repository.login(any(), any()) } returns Result.failure(Exception(errorMsg))

        // When
        viewModel.login("user", "pass")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertFalse(viewModel.isLoggedIn.value)
        assertEquals(errorMsg, viewModel.errorMessage.value)
    }

    @Test
    fun `addToWatchlist updates user state on success`() = runTest(testDispatcher) {
        // Given
        val movieId = 100
        val token = "token"
        val userResponse = MockUserRepository.UserResponse(
            id = "1", pseudo = "user", email = "test@test.com", 
            watchlist = listOf(movieId), likes = emptyList(), watched = emptyList(), 
            createdAt = "", updatedAt = ""
        )
        val expectedUser = UserModel(
            id = "1", pseudo = "user", email = "test@test.com", password = "",
            watchlist = listOf(movieId).toMutableList(), 
            likes = emptyList<Int>().toMutableList(), 
            watched = emptyList<Int>().toMutableList(), 
            createdAt = "", updatedAt = ""
        )
        
        every { securePrefs.getAuthToken() } returns token
        coEvery { repository.addToWatchlist(token, movieId) } returns Result.success(userResponse)

        // When
        viewModel.addToWatchlist(movieId)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(expectedUser, viewModel.currentUser.value)
    }
}
