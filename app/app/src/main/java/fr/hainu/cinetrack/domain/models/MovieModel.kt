package fr.hainu.cinetrack.domain.models



/**
 * Modèle pour un film
 * @param id: L'ID du film sur TMDB
 * @param title: Le titre du film
 * @param rating: La note du film sur TMDB
 * @param posterUrl: L'URL de l'affiche du film
 * @param backdropUrl: L'URL de l'affiche de fond du film
 * @param year: L'année de sortie du film
 * @param genres: Les genres du film
 * @param ratingCoef: Le coefficient de note sur TMDB
 * @param duration: La durée du film
 * @param synopsis: Le résumé du film
 * @param trailerUrl: L'URL de la bande-annonce du film
 * @param cast: La liste des membres du cast du film
 * @param reviews: La liste des commentaires et notes internes pour le film
 * si l'utilisateur a laissé un commentaire il doit être placé en premier dans la liste,
 * ne récupérer que 3 commentaires à l'instanciation, les autres seront récupéré si l'utilisateur cliques sur voir plus via une methode
 * @param isOnFavorite: Indique si le film est dans les favoris
 * @param isOnWatchlist: Indique si le film est dans la watchlist
 * @param isOnWatched: Indique si le film a été vu
 * @param isRated: Indique si le film a été noté
 * @param similarMovies: Liste des films similaires
 **/
data class MovieModel(
    val id: Int = 0,
    val title: String = "",
    val rating: Double = 0.0,
    val posterUrl: String? = "",
    val backdropUrl: String? = "",
    val year: String = "",
    var genres: String = "",
    val ratingCoef: Int = 0,
    var duration: String = "",
    val synopsis: String? = "",
    var trailerUrl: String? = "",
    var cast: List<CastMemberModel> = emptyList(),
    val reviews: List<ReviewModel> = emptyList(),
    var isOnFavorite: Boolean = false,
    var isOnWatchlist: Boolean = false,
    var isOnWatched: Boolean = false,
    val isRated: Boolean = false,
    var isDetailed: Boolean = false,
    var similarMovies: List<MovieModel> = emptyList()
)
