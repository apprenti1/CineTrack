package fr.hainu.cinetrack.ui.mock

import com.google.gson.Gson
import com.google.gson.JsonObject
import fr.hainu.cinetrack.BuildConfig
import fr.hainu.cinetrack.domain.models.CastMemberModel
import fr.hainu.cinetrack.domain.models.MovieModel
<<<<<<< HEAD
import fr.hainu.cinetrack.domain.models.CastMemberModel
import java.net.HttpURLConnection
import java.net.URL
import kotlin.collections.joinToString
=======
import java.net.HttpURLConnection
import java.net.URL
>>>>>>> d6b09d2b72ad70fcd721470e9f58066faaa7dd3d

class MockMovieRepository {

    private val gson = Gson()
    private val apiKey = BuildConfig.TMDB_API_KEY
    private val baseUrl = BuildConfig.TMDB_BASE_URL
    private val imageUrl = BuildConfig.TMDB_IMAGE_URL
    private val posterSize = BuildConfig.TMDB_IMAGE_POSTERSIZE
    private val backdropSize = BuildConfig.TMDB_IMAGE_BACKDROPSIZE


    enum class MovieType {
        TREND_WEEK,
        TREND,
        RECENT
    }

    fun getMovies(type: MovieType): List<MovieModel> {

        try {
            val connection: HttpURLConnection = when (type) {
                MovieType.TREND_WEEK -> URL("${baseUrl}trending/movie/week?api_key=$apiKey&language=fr-FR").openConnection() as HttpURLConnection
                MovieType.TREND -> URL("${baseUrl}movie/popular?api_key=$apiKey&language=fr-FR&page=1").openConnection() as HttpURLConnection
                MovieType.RECENT -> URL("${baseUrl}movie/now_playing?api_key=$apiKey&language=fr-FR&page=1").openConnection() as HttpURLConnection
            }
            connection.requestMethod = "GET"
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonObject = gson.fromJson(json, JsonObject::class.java)
                val results = jsonObject.getAsJsonArray("results")
                val movies: MutableList<MovieModel> = emptyList<MovieModel>().toMutableList()
                results.forEach { movieElement ->
                    val trendingMovie = movieElement.asJsonObject
                    movies.add(
                        MovieModel(
                            id = trendingMovie.get("id").asInt,
                            title = trendingMovie.get("title").asString,
                            rating = trendingMovie.get("vote_average").asDouble,
                            posterUrl = if (trendingMovie.has("poster_path") && !trendingMovie.get("poster_path").isJsonNull) {
                                "${imageUrl}${posterSize}${trendingMovie.get("poster_path").asString}"
                            } else {
                                ""
                            },
                            backdropUrl = if (trendingMovie.has("backdrop_path") && !trendingMovie.get("backdrop_path").isJsonNull) {
                                "${imageUrl}${backdropSize}${trendingMovie.get("backdrop_path").asString}"
                            } else {
                                ""
                            },
                            year = if (trendingMovie.has("release_date") && !trendingMovie.get("release_date").isJsonNull) {
                                trendingMovie.get("release_date").asString.substringBefore("-")
                            } else {
                                ""
                            },
                            ratingCoef = trendingMovie.get("vote_count").asInt,
                            synopsis = if (trendingMovie.has("overview") && !trendingMovie.get("overview").isJsonNull) {
                                trendingMovie.get("overview").asString
                            } else {
                                ""
                            },
                        )
                    )
                }

                return movies


            } else {
                Exception("Echec de récupération").printStackTrace()
                return emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }

    }

    fun getMovieDetails(movie: MovieModel) {
        try {
            val detailsConnection =
                URL("${baseUrl}movie/${movie.id}?api_key=$apiKey&language=fr-FR&append_to_response=videos,credits").openConnection() as HttpURLConnection
            detailsConnection.requestMethod = "GET"
            detailsConnection.setRequestProperty("Accept", "application/json")

            if (detailsConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = detailsConnection.inputStream.bufferedReader().use { it.readText() }
                val movieDetails = gson.fromJson(json, JsonObject::class.java)

                movie.duration = movieDetails.get("runtime")?.asInt?.let { "${it / 60}h ${it % 60}min" } ?: ""
                movie.genres = movieDetails.getAsJsonArray("genres").joinToString(", ") { it.asJsonObject.get("name").asString }
                movie.cast = movieDetails.getAsJsonObject("credits").getAsJsonArray("cast").map { castElement ->
                    val castObj = castElement.asJsonObject
                    CastMemberModel(
                        name = castObj.get("name").asString,
                        profilePictureUrl = if (castObj.has("profile_path") && !castObj.get("profile_path").isJsonNull) {
                            "${imageUrl}w185${castObj.get("profile_path").asString}"
                        } else ""
                    )
                }
                val videos = movieDetails.getAsJsonObject("videos")?.getAsJsonArray("results")
                val trailer = videos?.find { val videoObj = it.asJsonObject; videoObj.get("site").asString == "YouTube" && videoObj.get("type").asString == "Trailer" }?.asJsonObject
                movie.trailerUrl = if (trailer != null) "https://www.youtube.com/watch?v=${trailer.get("key").asString}" else ""
                movie.isDetailed = true

            } else {
                Exception("Failed to fetch movie details").printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMoviesWithSearch(query: String): List<MovieModel> {
        try {
            val connection: HttpURLConnection = URL("${baseUrl}search/movie?api_key=$apiKey&language=fr-FR&query=${query.replace(" ", "%20")}&page=1&include_adult=false").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonObject = gson.fromJson(json, JsonObject::class.java)
                val results = jsonObject.getAsJsonArray("results")
                val movies: MutableList<MovieModel> = emptyList<MovieModel>().toMutableList()
                results.forEach { movieElement ->
                    val searchMovie = movieElement.asJsonObject
                    movies.add(
                        MovieModel(
                            id = searchMovie.get("id").asInt,
                            title = searchMovie.get("title").asString,
                            rating = searchMovie.get("vote_average").asDouble,
                            posterUrl = if (searchMovie.has("poster_path") && !searchMovie.get("poster_path").isJsonNull) {
                                "${imageUrl}${posterSize}${searchMovie.get("poster_path").asString}"
                            } else {
                                ""
                            },
                            backdropUrl = if (searchMovie.has("backdrop_path") && !searchMovie.get("backdrop_path").isJsonNull) {
                                "${imageUrl}${backdropSize}${searchMovie.get("backdrop_path").asString}"
                            } else {
                                ""
                            },
                            year = if (searchMovie.has("release_date") && !searchMovie.get("release_date").isJsonNull) {
                                searchMovie.get("release_date").asString.substringBefore("-")
                            } else {
                                ""
                            },
                            ratingCoef = searchMovie.get("vote_count").asInt,
                            synopsis = if (searchMovie.has("overview") && !searchMovie.get("overview").isJsonNull) {
                                searchMovie.get("overview").asString
                            } else {
                                ""
                            },
                        )
                    )
                }
                return movies


            } else {
                Exception("Echec de récupération").printStackTrace()
                return emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun getMovieById(movieId: Int): MovieModel? {
        try {

            val connection = URL("${baseUrl}movie/${movieId}?api_key=$apiKey&language=fr-FR&append_to_response=videos,credits").openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Accept", "application/json")

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val json = connection.inputStream.bufferedReader().use { it.readText() }
                val movieObj = gson.fromJson(json, JsonObject::class.java)
                val trailer = movieObj.getAsJsonObject("videos")?.getAsJsonArray("results")?.find { val videoObj = it.asJsonObject; videoObj.get("site").asString == "YouTube" && videoObj.get("type").asString == "Trailer" }?.asJsonObject

                return MovieModel(
                    id = movieObj.get("id").asInt,
                    title = movieObj.get("title").asString,
                    rating = movieObj.get("vote_average").asDouble,
                    posterUrl = if (movieObj.has("poster_path") && !movieObj.get("poster_path").isJsonNull) {
                        "${imageUrl}${posterSize}${movieObj.get("poster_path").asString}"
                    } else {
                        ""
                    },
                    backdropUrl = if (movieObj.has("backdrop_path") && !movieObj.get("backdrop_path").isJsonNull) {
                        "${imageUrl}${backdropSize}${movieObj.get("backdrop_path").asString}"
                    } else {
                        ""
                    },
                    year = if (movieObj.has("release_date") && !movieObj.get("release_date").isJsonNull) {
                        movieObj.get("release_date").asString.substringBefore("-")
                    } else {
                        ""
                    },
                    genres = movieObj.getAsJsonArray("genres").joinToString(", ") { it.asJsonObject.get("name").asString },
                    ratingCoef = movieObj.get("vote_count").asInt,
                    duration = movieObj.get("runtime")?.asInt?.let { "${it / 60}h ${it % 60}min" } ?: "",
                    synopsis = if (movieObj.has("overview") && !movieObj.get("overview").isJsonNull) {
                        movieObj.get("overview").asString
                    } else {
                        ""
                    },
                    cast = movieObj.getAsJsonObject("credits").getAsJsonArray("cast").map { castElement ->
                        val castObj = castElement.asJsonObject
                        CastMemberModel(
                            name = castObj.get("name").asString,
                            profilePictureUrl = if (castObj.has("profile_path") && !castObj.get("profile_path").isJsonNull) {
                                "${imageUrl}w185${castObj.get("profile_path").asString}"
                            } else ""
                        )
                    },
                    trailerUrl = if (trailer != null) "https://www.youtube.com/watch?v=${trailer.get("key").asString}" else "",
                    isDetailed = true
                )
            } else {
                Exception("Failed to fetch movie by ID").printStackTrace()
                return null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    suspend fun getMoviesByIds(movieIds: List<Int>): List<MovieModel> {
        return movieIds.mapNotNull { id ->
            try {
                getMovieById(id)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}