package fr.hainu.cinetrack.domain.models

/**
 * Modèle pour un utilisateur
 * @param id: L'ID unique de l'utilisateur
 * @param pseudo: Le pseudonyme de l'utilisateur
 * @param email: L'adresse email de l'utilisateur
 * @param password: Le mot de passe de l'utilisateur (devrait être hashé)
 * @param watchlist: La liste des IDs de films dans la watchlist
 * @param likes: La liste des IDs de films aimés
 * @param createdAt: La date de création du compte
 * @param updatedAt: La date de dernière modification du compte
 * @param reviews: La liste des reviews/commentaires de l'utilisateur
 * @param lists: La liste des listes personnalisées de l'utilisateur
 **/
data class UserModel(
    val id: String,
    val pseudo: String,
    val email: String,
    val password: String,
    val watchlist: List<Int> = emptyList(),
    val likes: List<Int> = emptyList(),
    val createdAt: String,
    val updatedAt: String,
    val reviews: List<ReviewModel> = emptyList(),
    val lists: List<ListModel> = emptyList()
)