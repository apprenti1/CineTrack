package fr.hainu.cinetrack.domain.models

/**
 * Modèle pour un commentaire et une note interne
 * @param id: L'ID du commentaire
 * @param comment: Le texte du commentaire
 * @param rating: La note donnée (généralement sur 5 ou 10)
 * @param refUser: L'ID de référence de l'utilisateur qui a créé le commentaire
 * @param userName: Le pseudo de l'utilisateur
 * @param refMovie: L'ID de référence du film commenté
 * @param createdAt: La date de création du commentaire
 **/
data class ReviewModel(
    val id: Int,
    val comment: String,
    val rating: Int,
    val refUser: String,
    val userName: String,
    val refMovie: Int,
    val createdAt: String
)