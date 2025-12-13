package fr.hainu.cinetrack.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

object RetrofitInstance {
    private const val TMDB_API_URL = "https://api.themoviedb.org/3/"
    //To fill
    private const val DB_API_URL = ""
    private const val TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MGQyNzI0ZjllODIyNzQ3M2VmMWIwNThkNTZiM2M0MSIsIm5iZiI6MTc2NTMxMjQ2Mi4yNjksInN1YiI6IjY5Mzg4N2NlODYyNTNiNDczOWFmMDA4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.A-8tN8w5FtnmPp8V7EnwbFoLJdyfKVQFMLLNHH79wZo"
    private const val DB_TOKEN = ""

    //The Movie Data Bse HTTP client
    private val okHttpClientTmdb: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer $TOKEN")
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    //DB HTTP client
    private val okHttpClientDB: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer $DB_TOKEN")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    //The Movie Data Bse HTTP API
    val apiTmdb: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(TMDB_API_URL)
            .client(okHttpClientTmdb)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Retrofit::class.java)
    }

    //DB API
    val apiDB: Retrofit =
        Retrofit.Builder()
            .baseUrl(DB_API_URL)
            .client(okHttpClientDB)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Retrofit::class.java)

}

/*prof :
object RetrofitInstance {
    private val BASE_URL = "https://my-json-server.typicode.com/RamzyK/"

    val api: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
//work in progress
*/