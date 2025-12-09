package fr.hainu.cinetrack.ui.models

/**
 * Modèle pour une liste de films personnalisée
 * @param id: L'ID unique de la liste
 * @param name: Le nom de la liste
 * @param userId: L'ID de l'utilisateur propriétaire de la liste
 * @param filmIds: La liste des IDs de films contenus dans cette liste
 * @param createdAt: La date de création de la liste
 * @param updatedAt: La date de dernière modification de la liste
 **/
data class ListModel(
    val id: Int,
    val name: String,
    val userId: String,
    val filmIds: List<Int> = emptyList(),
    val createdAt: String,
    val updatedAt: String
)