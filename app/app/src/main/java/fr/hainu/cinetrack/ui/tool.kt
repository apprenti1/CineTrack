package fr.hainu.cinetrack.ui

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun getRandomColor(): Color {
    return Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
}