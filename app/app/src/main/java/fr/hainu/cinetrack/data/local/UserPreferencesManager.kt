package fr.hainu.cinetrack.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Gestionnaire de préférences utilisateur avec DataStore
 * Remplace SecurePreferencesManager avec une API asynchrone moderne
 */
class UserPreferencesManager(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

        // Clés des préférences
        private val KEY_AUTH_TOKEN = stringPreferencesKey("auth_token")
        private val KEY_USER_ID = stringPreferencesKey("user_id")
        private val KEY_USER_PSEUDO = stringPreferencesKey("user_pseudo")
        private val KEY_USER_EMAIL = stringPreferencesKey("user_email")
        private val KEY_HAS_COMPLETED_ONBOARDING = booleanPreferencesKey("has_completed_onboarding")
    }

    // Auth Token
    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_AUTH_TOKEN] = token
        }
    }

    suspend fun getAuthToken(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_AUTH_TOKEN]
        }.first()
    }

    fun getAuthTokenFlow(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_AUTH_TOKEN]
        }
    }

    suspend fun clearAuthToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_AUTH_TOKEN)
        }
    }

    // User ID
    suspend fun saveUserId(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USER_ID] = userId
        }
    }

    suspend fun getUserId(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_USER_ID]
        }.first()
    }

    // User Info (pseudo & email)
    suspend fun saveUserInfo(pseudo: String, email: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USER_PSEUDO] = pseudo
            preferences[KEY_USER_EMAIL] = email
        }
    }

    suspend fun getUserPseudo(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_USER_PSEUDO]
        }.first()
    }

    suspend fun getUserEmail(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_USER_EMAIL]
        }.first()
    }

    // Onboarding
    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_HAS_COMPLETED_ONBOARDING] = completed
        }
    }

    suspend fun hasCompletedOnboarding(): Boolean {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_HAS_COMPLETED_ONBOARDING] ?: false
        }.first()
    }

    fun hasCompletedOnboardingFlow(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_HAS_COMPLETED_ONBOARDING] ?: false
        }
    }

    // Check if user is logged in
    suspend fun isLoggedIn(): Boolean {
        return getAuthToken() != null
    }

    fun isLoggedInFlow(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_AUTH_TOKEN] != null
        }
    }

    // Clear all user data
    suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_AUTH_TOKEN)
            preferences.remove(KEY_USER_ID)
            preferences.remove(KEY_USER_PSEUDO)
            preferences.remove(KEY_USER_EMAIL)
        }
    }
}
