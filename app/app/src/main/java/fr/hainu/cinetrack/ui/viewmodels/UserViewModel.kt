package fr.hainu.cinetrack.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.data.local.UserPreferencesManager
import fr.hainu.cinetrack.domain.models.UserModel
import fr.hainu.cinetrack.domain.usecase.user.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userUseCases: UserUseCase,
    private val userPrefs: UserPreferencesManager
) : ViewModel() {

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
        viewModelScope.launch {
            // Observer l'état de l'onboarding avec Flow
            launch {
                userPrefs.hasCompletedOnboardingFlow().collect { completed ->
                    _hasCompletedOnboarding.value = completed
                }
            }

            // Observer l'état de connexion avec Flow
            launch {
                userPrefs.isLoggedInFlow().collect { loggedIn ->
                    _isLoggedIn.value = loggedIn
                    if (loggedIn && _currentUser.value == null) {
                        loadUserProfile()
                    }
                }
            }
        }
    }

    fun login(pseudo: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = userUseCases.login(pseudo, password)
                result.onSuccess { (token, userModel) ->
                    // Sauvegarder le token et les infos utilisateur dans DataStore
                    userPrefs.saveAuthToken(token)
                    userPrefs.saveUserId(userModel.id)
                    userPrefs.saveUserInfo(userModel.pseudo, userModel.email)

                    _currentUser.value = userModel
                }.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Erreur lors de la connexion : ${e.message}"
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
                val result = userUseCases.register(pseudo, email, password)
                result.onSuccess { (token, userModel) ->
                    // Sauvegarder le token et les infos utilisateur dans DataStore
                    userPrefs.saveAuthToken(token)
                    userPrefs.saveUserId(userModel.id)
                    userPrefs.saveUserInfo(userModel.pseudo, userModel.email)

                    _currentUser.value = userModel
                }.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Erreur lors de l'inscription : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPrefs.clearAll()
            _currentUser.value = null
        }
    }

    private fun loadUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val result = userUseCases.getProfile()
                result.onSuccess { userModel ->
                    _currentUser.value = userModel
                }.onFailure { error ->
                    _errorMessage.value = error.message
                    userPrefs.clearAll()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Erreur lors du chargement du profil : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addToWatchlist(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("UserViewModel", "addToWatchlist called for movieId: $movieId")
                val result = userUseCases.addToWatchlist(movieId)
                result.onSuccess { userModel ->
                    Log.d("UserViewModel", "addToWatchlist success: watchlist=${userModel.watchlist}")
                    _currentUser.value = userModel
                    Log.d("UserViewModel", "Updated currentUser: ${_currentUser.value?.watchlist}")
                }.onFailure { error ->
                    Log.e("UserViewModel", "addToWatchlist failed: ${error.message}")
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "addToWatchlist exception", e)
                e.printStackTrace()
                _errorMessage.value = "Erreur lors de l'ajout à la watchlist : ${e.message}"
            }
        }
    }

    fun removeFromWatchlist(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userUseCases.removeFromWatchlist(movieId)
                result.onSuccess { userModel ->
                    _currentUser.value = userModel
                }.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Erreur lors du retrait de la watchlist : ${e.message}"
            }
        }
    }

    fun addToLikes(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("UserViewModel", "addToLikes called for movieId: $movieId")
                val result = userUseCases.addToLikes(movieId)
                result.onSuccess { userModel ->
                    Log.d("UserViewModel", "addToLikes success: likes=${userModel.likes}")
                    _currentUser.value = userModel
                    Log.d("UserViewModel", "Updated currentUser: ${_currentUser.value?.likes}")
                }.onFailure { error ->
                    Log.e("UserViewModel", "addToLikes failed: ${error.message}")
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "addToLikes exception", e)
                e.printStackTrace()
                _errorMessage.value = "Erreur lors de l'ajout aux likes : ${e.message}"
            }
        }
    }

    fun removeFromLikes(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userUseCases.removeFromLikes(movieId)
                result.onSuccess { userModel ->
                    _currentUser.value = userModel
                }.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Erreur lors du retrait des likes : ${e.message}"
            }
        }
    }

    fun addToWatched(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("UserViewModel", "addToWatched called for movieId: $movieId")
                val result = userUseCases.addToWatched(movieId)
                result.onSuccess { userModel ->
                    Log.d("UserViewModel", "addToWatched success: watched=${userModel.watched}")
                    _currentUser.value = userModel
                    Log.d("UserViewModel", "Updated currentUser: ${_currentUser.value?.watched}")
                }.onFailure { error ->
                    Log.e("UserViewModel", "addToWatched failed: ${error.message}")
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "addToWatched exception", e)
                e.printStackTrace()
                _errorMessage.value = "Erreur lors de l'ajout aux films vus : ${e.message}"
            }
        }
    }

    fun removeFromWatched(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userUseCases.removeFromWatched(movieId)
                result.onSuccess { userModel ->
                    _currentUser.value = userModel
                }.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Erreur lors du retrait des films vus : ${e.message}"
            }
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            userPrefs.setOnboardingCompleted(true)
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
