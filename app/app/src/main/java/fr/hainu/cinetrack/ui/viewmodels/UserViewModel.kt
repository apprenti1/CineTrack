package fr.hainu.cinetrack.ui.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.hainu.cinetrack.domain.models.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("CineTrackPrefs", Context.MODE_PRIVATE)

    private val _currentUser = MutableStateFlow<UserModel?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _hasCompletedOnboarding = MutableStateFlow(false)
    val hasCompletedOnboarding = _hasCompletedOnboarding.asStateFlow()

    init {
        // Charger l'état de l'onboarding au démarrage
        _hasCompletedOnboarding.value = sharedPreferences.getBoolean("has_completed_onboarding", false)
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Appel au repository pour authentifier l'utilisateur
                // val user = repository.login(email, password)
                // _currentUser.value = user
                // _isLoggedIn.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _isLoggedIn.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun register(pseudo: String, email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Appel au repository pour créer un compte utilisateur
                // val user = repository.register(pseudo, email, password)
                // _currentUser.value = user
                // _isLoggedIn.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _isLoggedIn.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            // TODO: Nettoyer les données de session (tokens, cache, etc.)
            _currentUser.value = null
            _isLoggedIn.value = false
        }
    }

    fun updateProfile(pseudo: String? = null, email: String? = null) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val user = _currentUser.value
                if (user != null) {
                    // TODO: Appel au repository pour mettre à jour le profil
                    // val updatedUser = repository.updateProfile(user.id, pseudo, email)
                    // _currentUser.value = updatedUser
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addToWatchlist(movieId: Int) {
        viewModelScope.launch {
            try {
                val user = _currentUser.value
                if (user != null) {
                    // TODO: Appel au repository pour ajouter à la watchlist
                    // repository.addToWatchlist(user.id, movieId)
                    // Mettre à jour l'utilisateur local
                    // _currentUser.value = user.copy(watchlist = user.watchlist + movieId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeFromWatchlist(movieId: Int) {
        viewModelScope.launch {
            try {
                val user = _currentUser.value
                if (user != null) {
                    // TODO: Appel au repository pour retirer de la watchlist
                    // repository.removeFromWatchlist(user.id, movieId)
                    // Mettre à jour l'utilisateur local
                    // _currentUser.value = user.copy(watchlist = user.watchlist - movieId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addToLikes(movieId: Int) {
        viewModelScope.launch {
            try {
                val user = _currentUser.value
                if (user != null) {
                    // TODO: Appel au repository pour ajouter aux likes
                    // repository.addToLikes(user.id, movieId)
                    // _currentUser.value = user.copy(likes = user.likes + movieId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeFromLikes(movieId: Int) {
        viewModelScope.launch {
            try {
                val user = _currentUser.value
                if (user != null) {
                    // TODO: Appel au repository pour retirer des likes
                    // repository.removeFromLikes(user.id, movieId)
                    // _currentUser.value = user.copy(likes = user.likes - movieId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadUserProfile(userId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Appel au repository pour charger le profil utilisateur
                // val user = repository.getUserProfile(userId)
                // _currentUser.value = user
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun completeOnboarding() {
        sharedPreferences.edit().putBoolean("has_completed_onboarding", true).apply()
        _hasCompletedOnboarding.value = true
    }
}
