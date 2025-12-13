package fr.hainu.cinetrack.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.data.local.SecurePreferencesManager
import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.ui.mock.MockUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val securePrefs = SecurePreferencesManager(application)
    private val repository = MockUserRepository()

    private val _currentUser = MutableStateFlow<UserModel?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _hasCompletedOnboarding = MutableStateFlow(false)
    val hasCompletedOnboarding = _hasCompletedOnboarding.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        // Charger l'état de l'onboarding au démarrage
        _hasCompletedOnboarding.value = securePrefs.hasCompletedOnboarding()

        // Charger le token si existant
        if (securePrefs.isLoggedIn()) {
            _isLoggedIn.value = true
            loadUserProfile()
        }
    }

    fun login(pseudo: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.login(pseudo, password)
                result.onSuccess { authResponse ->
                    // Sauvegarder dans le stockage sécurisé
                    securePrefs.saveAuthToken(authResponse.accessToken)
                    securePrefs.saveUserId(authResponse.user.id)
                    securePrefs.saveUserInfo(authResponse.user.pseudo, authResponse.user.email)

                    _currentUser.value = UserModel(
                        id = authResponse.user.id,
                        pseudo = authResponse.user.pseudo,
                        email = authResponse.user.email,
                        password = "",
                        watchlist = authResponse.user.watchlist,
                        likes = authResponse.user.likes,
                        createdAt = authResponse.user.createdAt,
                        updatedAt = authResponse.user.updatedAt
                    )
                    _isLoggedIn.value = true
                }.onFailure { error ->
                    _errorMessage.value = error.message
                    _isLoggedIn.value = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
                _isLoggedIn.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun register(pseudo: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.register(pseudo, email, password)
                result.onSuccess { authResponse ->
                    // Sauvegarder dans le stockage sécurisé
                    securePrefs.saveAuthToken(authResponse.accessToken)
                    securePrefs.saveUserId(authResponse.user.id)
                    securePrefs.saveUserInfo(authResponse.user.pseudo, authResponse.user.email)

                    _currentUser.value = UserModel(
                        id = authResponse.user.id,
                        pseudo = authResponse.user.pseudo,
                        email = authResponse.user.email,
                        password = "",
                        watchlist = authResponse.user.watchlist,
                        likes = authResponse.user.likes,
                        createdAt = authResponse.user.createdAt,
                        updatedAt = authResponse.user.updatedAt
                    )
                    _isLoggedIn.value = true
                }.onFailure { error ->
                    _errorMessage.value = error.message
                    _isLoggedIn.value = false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
                _isLoggedIn.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            securePrefs.clearAll()
            _currentUser.value = null
            _isLoggedIn.value = false
        }
    }

    private fun loadUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val token = securePrefs.getAuthToken()
                token?.let {
                    val result = repository.getProfile(it)
                    result.onSuccess { userResponse ->
                        _currentUser.value = UserModel(
                            id = userResponse.id,
                            pseudo = userResponse.pseudo,
                            email = userResponse.email,
                            password = "",
                            watchlist = userResponse.watchlist,
                            likes = userResponse.likes,
                            createdAt = userResponse.createdAt,
                            updatedAt = userResponse.updatedAt
                        )
                    }.onFailure { error ->
                        _errorMessage.value = error.message
                        _isLoggedIn.value = false
                        securePrefs.clearAll()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addToWatchlist(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = securePrefs.getAuthToken()
                token?.let {
                    val result = repository.addToWatchlist(it, movieId)
                    result.onSuccess { userResponse ->
                        _currentUser.value = _currentUser.value?.copy(
                            watchlist = userResponse.watchlist
                        )
                    }.onFailure { error ->
                        _errorMessage.value = error.message
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
            }
        }
    }

    fun removeFromWatchlist(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = securePrefs.getAuthToken()
                token?.let {
                    val result = repository.removeFromWatchlist(it, movieId)
                    result.onSuccess { userResponse ->
                        _currentUser.value = _currentUser.value?.copy(
                            watchlist = userResponse.watchlist
                        )
                    }.onFailure { error ->
                        _errorMessage.value = error.message
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
            }
        }
    }

    fun addToLikes(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = securePrefs.getAuthToken()
                token?.let {
                    val result = repository.addToLikes(it, movieId)
                    result.onSuccess { userResponse ->
                        _currentUser.value = _currentUser.value?.copy(
                            likes = userResponse.likes
                        )
                    }.onFailure { error ->
                        _errorMessage.value = error.message
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
            }
        }
    }

    fun removeFromLikes(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = securePrefs.getAuthToken()
                token?.let {
                    val result = repository.removeFromLikes(it, movieId)
                    result.onSuccess { userResponse ->
                        _currentUser.value = _currentUser.value?.copy(
                            likes = userResponse.likes
                        )
                    }.onFailure { error ->
                        _errorMessage.value = error.message
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
            }
        }
    }

    fun completeOnboarding() {
        securePrefs.setOnboardingCompleted(true)
        _hasCompletedOnboarding.value = true
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
