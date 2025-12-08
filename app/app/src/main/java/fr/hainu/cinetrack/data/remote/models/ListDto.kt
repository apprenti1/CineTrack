package fr.hainu.cinetrack.data.remote.models

data class ListDto(
    val id: Int,
    val name: String,
    val userId: String,
    val filmIds: List<Int> = emptyList(),
    val createdAt: String,
    val updatedAt: String
)
{}