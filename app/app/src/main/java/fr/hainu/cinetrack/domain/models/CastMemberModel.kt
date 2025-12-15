package fr.hainu.cinetrack.domain.models

import androidx.compose.ui.graphics.Color
import fr.hainu.cinetrack.ui.theme.Indigo600
import fr.hainu.cinetrack.ui.theme.Purple700

/**
 * Modèle pour un membre du cast d'un film
 * @param name: Le nom de l'acteur/actrice
 * @param profilePictureUrl: L'URL de la photo de profil
 */
data class CastMemberModel(
    val id: Int,
    val name: String,
    val profilePictureUrl: String,
)
