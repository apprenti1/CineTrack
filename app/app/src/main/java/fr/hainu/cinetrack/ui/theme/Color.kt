package fr.hainu.cinetrack.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Dark colors
private val DarkPurple900 = Color(0xFF581C87)
private val DarkPurple700 = Color(0xFF7E22CE)
private val DarkPurple600 = Color(0xFF9333EA)
private val DarkPurple500 = Color(0xFFA78BFA)
private val DarkPurple400 = Color(0xFFA855F7)
private val DarkPurple200 = Color(0xFFE9D5FF)
private val DarkPink600 = Color(0xFFDB2777)
private val DarkRose500 = Color(0xFFF43F5E)
private val DarkBlue600 = Color(0xFF2563EB)
private val DarkCyan600 = Color(0xFF06B6D4)
private val DarkGray900 = Color(0xFF111827)
private val DarkGray800 = Color(0xFF1F2937)
private val DarkGray600 = Color(0xFF4B5563)
private val DarkGray400 = Color(0xFF9CA3AF)
private val DarkGray300 = Color(0xFFD1D5DB)
private val DarkYellow400 = Color(0xFFFBBF24)
private val DarkRed600 = Color(0xFFDC2626)
private val DarkIndigo600 = Color(0xFF4F46E5)

// Light colors
private val LightPurple900 = Color(0xFF55366d)
private val LightPurple700 = Color(0xFF7a5699)
private val LightPurple600 = Color(0xFF9079a4)
private val LightPurple500 = Color(0xFFbfbdc7)
private val LightPurple400 = Color(0xFFa69eae)
private val LightPurple200 = Color(0xFFebebeb)
private val LightPink600 = Color(0xFFa65e7e)
private val LightRose500 = Color(0xFFa48e92)
private val LightBlue600 = Color(0xFF73809c)
private val LightCyan600 = Color(0xFF677274)
private val LightGray900 = Color(0xFFf6fafe)
private val LightGray800 = Color(0xFFecf2fe)
private val LightGray600 = Color(0xFFb2cffa)
private val LightGray400 = Color(0xFF5691f5)
private val LightGray300 = Color(0xFF0b52e0)
private val LightYellow400 = Color(0xFF93918a)
private val LightRed600 = Color(0xFFa55f5f)
private val LightIndigo600 = Color(0xFF7f7cb1)

// Classe pour stocker les couleurs du thème
data class CineTrackColors(
    val Purple900: Color,
    val Purple700: Color,
    val Purple600: Color,
    val Purple500: Color,
    val Purple400: Color,
    val Purple200: Color,
    val Pink600: Color,
    val Rose500: Color,
    val Blue600: Color,
    val Cyan600: Color,
    val Gray900: Color,
    val Gray800: Color,
    val Gray600: Color,
    val Gray400: Color,
    val Gray300: Color,
    val Yellow400: Color,
    val Red600: Color,
    val Indigo600: Color
)

// Palettes de couleurs pour chaque thème
val DarkColors = CineTrackColors(
    Purple900 = DarkPurple900,
    Purple700 = DarkPurple700,
    Purple600 = DarkPurple600,
    Purple500 = DarkPurple500,
    Purple400 = DarkPurple400,
    Purple200 = DarkPurple200,
    Pink600 = DarkPink600,
    Rose500 = DarkRose500,
    Blue600 = DarkBlue600,
    Cyan600 = DarkCyan600,
    Gray900 = DarkGray900,
    Gray800 = DarkGray800,
    Gray600 = DarkGray600,
    Gray400 = DarkGray400,
    Gray300 = DarkGray300,
    Yellow400 = DarkYellow400,
    Red600 = DarkRed600,
    Indigo600 = DarkIndigo600
)

val LightColors = CineTrackColors(
    Purple900 = LightPurple900,
    Purple700 = LightPurple700,
    Purple600 = LightPurple600,
    Purple500 = LightPurple500,
    Purple400 = LightPurple400,
    Purple200 = LightPurple200,
    Pink600 = LightPink600,
    Rose500 = LightRose500,
    Blue600 = LightBlue600,
    Cyan600 = LightCyan600,
    Gray900 = LightGray900,
    Gray800 = LightGray800,
    Gray600 = LightGray600,
    Gray400 = LightGray400,
    Gray300 = LightGray300,
    Yellow400 = LightYellow400,
    Red600 = LightRed600,
    Indigo600 = LightIndigo600
)

// CompositionLocal pour accéder aux couleurs
val LocalAppColors = staticCompositionLocalOf { DarkColors }

// Propriété pour accéder aux couleurs facilement
private val AppColors: CineTrackColors
    @Composable
    @ReadOnlyComposable
    get() = LocalAppColors.current

// Accesseurs pour utiliser directement les couleurs comme avant
val Purple900: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Purple900

val Purple700: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Purple700

val Purple600: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Purple600

val Purple500: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Purple500

val Purple400: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Purple400

val Purple200: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Purple200

val Pink600: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Pink600

val Rose500: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Rose500

val Blue600: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Blue600

val Cyan600: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Cyan600

val Gray900: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Gray900

val Gray800: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Gray800

val Gray600: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Gray600

val Gray400: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Gray400

val Gray300: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Gray300

val Yellow400: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Yellow400

val Red600: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Red600

val Indigo600: Color
    @Composable
    @ReadOnlyComposable
    get() = AppColors.Indigo600
