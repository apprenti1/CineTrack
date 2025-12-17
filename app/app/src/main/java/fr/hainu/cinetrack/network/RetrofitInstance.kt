package fr.hainu.cinetrack.network

import fr.hainu.cinetrack.BuildConfig
import fr.hainu.cinetrack.data.local.UserPreferencesManager
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {
    // URLs depuis BuildConfig
    private val TMDB_BASE_URL = BuildConfig.TMDB_BASE_URL
    private val TMDB_API_KEY = BuildConfig.TMDB_API_KEY
    private val CINETRACK_API_URL = "${BuildConfig.CINETRACK_API_URL}/api/"

    // Logging interceptor pour le debug
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
    }

    // TMDB HTTP client avec API key dans les query params
    private val okHttpClientTmdb: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", TMDB_API_KEY)
                    .addQueryParameter("language", "fr-FR")
                    .build()

                val request = original.newBuilder()
                    .url(url)
                    .addHeader("Accept", "application/json")
                    .build()

                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // CineTrack API HTTP client avec token JWT dynamique
    fun createCineTrackClient(userPrefs: UserPreferencesManager? = null): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")

                // Ajouter le token si disponible (DataStore nécessite runBlocking dans l'interceptor)
                userPrefs?.let { prefs ->
                    val token = runBlocking { prefs.getAuthToken() }
                    token?.let {
                        requestBuilder.addHeader("Authorization", "Bearer $it")
                    }
                }

                chain.proceed(requestBuilder.build())
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // TMDB API instance
    val tmdbApi: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .client(okHttpClientTmdb)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // CineTrack API instance (sans token pour auth endpoints)
    val cineTrackApi: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(CINETRACK_API_URL)
            .client(createCineTrackClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // CineTrack API avec token
    fun getCineTrackApiWithAuth(userPrefs: UserPreferencesManager): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CINETRACK_API_URL)
            .client(createCineTrackClient(userPrefs))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}