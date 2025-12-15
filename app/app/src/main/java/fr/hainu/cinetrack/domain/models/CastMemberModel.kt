package fr.hainu.cinetrack.domain.models

/**
 * Modèle pour un membre du cast d'un film
 * @param name: Le nom de l'acteur/actrice
 * @param profilePictureUrl: L'URL de la photo de profil
 */
data class CastMemberModel(
    val name: String,
    val profilePictureUrl: String,
)
