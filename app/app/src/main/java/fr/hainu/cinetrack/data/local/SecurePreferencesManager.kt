package fr.hainu.cinetrack.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecurePreferencesManager(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedPrefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val regularPrefs: SharedPreferences =
        context.getSharedPreferences("CineTrackPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_PSEUDO = "user_pseudo"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_HAS_COMPLETED_ONBOARDING = "has_completed_onboarding"
    }

    // Auth Token (sécurisé)
    fun saveAuthToken(token: String) {
        encryptedPrefs.edit().putString(KEY_AUTH_TOKEN, token).apply()
    }

    fun getAuthToken(): String? {
        return encryptedPrefs.getString(KEY_AUTH_TOKEN, null)
    }

    fun clearAuthToken() {
        encryptedPrefs.edit().remove(KEY_AUTH_TOKEN).apply()
    }

    // User ID (sécurisé)
    fun saveUserId(userId: String) {
        encryptedPrefs.edit().putString(KEY_USER_ID, userId).apply()
    }

    fun getUserId(): String? {
        return encryptedPrefs.getString(KEY_USER_ID, null)
    }

    // User Info (régulier - pas sensible)
    fun saveUserInfo(pseudo: String, email: String) {
        regularPrefs.edit()
            .putString(KEY_USER_PSEUDO, pseudo)
            .putString(KEY_USER_EMAIL, email)
            .apply()
    }

    fun getUserPseudo(): String? {
        return regularPrefs.getString(KEY_USER_PSEUDO, null)
    }

    fun getUserEmail(): String? {
        return regularPrefs.getString(KEY_USER_EMAIL, null)
    }

    // Onboarding (régulier)
    fun setOnboardingCompleted(completed: Boolean) {
        regularPrefs.edit().putBoolean(KEY_HAS_COMPLETED_ONBOARDING, completed).apply()
    }

    fun hasCompletedOnboarding(): Boolean {
        return regularPrefs.getBoolean(KEY_HAS_COMPLETED_ONBOARDING, false)
    }

    // Clear all user data
    fun clearAll() {
        encryptedPrefs.edit().clear().apply()
        regularPrefs.edit()
            .remove(KEY_USER_PSEUDO)
            .remove(KEY_USER_EMAIL)
            .apply()
    }

    // Check if user is logged in
    fun isLoggedIn(): Boolean {
        return getAuthToken() != null
    }
}
