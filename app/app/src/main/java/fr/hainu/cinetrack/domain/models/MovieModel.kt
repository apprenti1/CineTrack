package fr.hainu.cinetrack.domain.models

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

private fun getRandomColor(): Color {
    return Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
}

/**
 * Modèle pour un film
 * @param id: L'ID du film sur TMDB
 * @param title: Le titre du film
 * @param rating: La note du film sur TMDB
 * @param posterUrl: L'URL de l'affiche du film
 * @param year: L'année de sortie du film
 * @param genres: Les genres du film
 * @param gradientStart: La couleur de départ du gradient de fond
 * @param gradientEnd: La couleur de fin du gradient de fond
 * @param ratingCoef: Le coefficient de note sur TMDB
 * @param duration: La durée du film
 * @param synopsis: Le résumé du film
 * @param trailerUrl: L'URL de la bande-annonce du film
 * @param internalCommentsAndRatings: La liste des commentaires et notes internes pour le film
 **/
data class MovieModel(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterUrl: String,
    val year: String,
    val genres: String,
    val gradientStart: Color = getRandomColor(),
    val gradientEnd: Color = getRandomColor(),
    val ratingCoef: Int = 0,
    val duration: String = "",
    val synopsis: String = "",
    val trailerUrl: String = "",
    val internalCommentsAndRatings: List<ReviewModel> = emptyList()
)
