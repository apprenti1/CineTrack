package fr.hainu.cinetrack.ui.models

/**
 * Modèle pour un commentaire et une note interne
 * @param id: L'ID du commentaire
 * @param comment: Le texte du commentaire
 * @param rating: La note donnée (généralement sur 5 ou 10)
 * @param refUser: L'ID de référence de l'utilisateur qui a créé le commentaire
 * @param refMovie: L'ID de référence du film commenté
 * @param userModel: Le modèle utilisateur associé (optionnel)
 * majoritairement utilisé par l'utilisateur donc pas toujours utile de le récupérer
 * @param movieModel: Le modèle film associé (optionnel)
 * majoritairement utilisé par un objet Movie donc pas toujours utile de le récupérer
 * @param createdAt: La date de création du commentaire
 * @param updatedAt: La date de mise à jour du commentaire
 **/
data class ReviewModel(
    val id: Int,
    val comment: String,
    val rating: Int,
    val refUser: Int,
    val refMovie: Int,
    val userModel: UserModel? = null,
    val movieModel: MovieModel? = null,
    val createdAt: String,
    val updatedAt: String
)