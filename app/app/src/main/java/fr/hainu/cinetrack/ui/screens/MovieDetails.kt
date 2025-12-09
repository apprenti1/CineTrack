package fr.hainu.cinetrack.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.hainu.cinetrack.ui.components.*
import fr.hainu.cinetrack.ui.getMockMovies
import fr.hainu.cinetrack.ui.models.MovieModel
import fr.hainu.cinetrack.ui.theme.*

@Composable
fun MovieDetailsScreen(
    movie: MovieModel,
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val movieTitle = movie.title
    val movieYear = movie.year

    val cast = movie.cast


    val similarMovies = listOf(
        MovieModel(
            0,
            "Avatar: The Way of Water",
            7.8,
            "https://fr.web.img2.acsta.net/c_310_420/pictures/22/12/14/10/06/4868282.jpg",
            "2022",
            "Science-fiction"
        ),
        MovieModel(0, "Black Panther: Wakanda Forever", 7.3, "https://fr.web.img6.acsta.net/c_310_420/pictures/22/11/03/10/06/2758283.jpg", "2022", "Action, Aventure"),
        MovieModel(0, "Top Gun: Maverick", 8.4, "https://fr.web.img5.acsta.net/c_310_420/pictures/22/05/16/10/06/2753601.jpg", "2022", "Action, Drame"),
    )

    val reviews = listOf(
        Review(
            userName = "Marie Dubois",
            rating = 5,
            comment = "Un chef-d'œuvre absolu ! Denis Villeneuve a réussi à surpasser le premier opus. Les visuels sont à couper le souffle et la bande-son de Hans Zimmer est magistrale. Une expérience cinématographique inoubliable.",
            timeAgo = "Il y a 2 jours",
            likes = 24,
            gradientStart = Blue600,
            gradientEnd = Purple600
        ),
        Review(
            userName = "Thomas Laurent",
            rating = 4,
            comment = "Excellent film, même si j'ai trouvé le rythme un peu lent par moments. Les performances des acteurs sont remarquables, notamment Timothée Chalamet et Zendaya. À voir absolument sur grand écran !",
            timeAgo = "Il y a 5 jours",
            likes = 12,
            gradientStart = Pink500,
            gradientEnd = Rose600
        ),
        Review(
            userName = "Sophie Martin",
            rating = 5,
            comment = "Incroyable continuation de l'histoire. La profondeur des personnages et la complexité de l'univers sont parfaitement rendues. Vivement le troisième volet !",
            timeAgo = "Il y a 1 semaine",
            likes = 31,
            gradientStart = Green600,
            gradientEnd = Emerald600
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray900)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MovieDetailsHeader(
                title = movieTitle,
                year = movieYear,
                backdropUrl = "https://image.tmdb.org/t/p/original/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg",
                onBackClick = onBackClick,
                onShareClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "$movieTitle ($movieYear)")
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Partager"))
                },
                gradientStart = Purple600,
                gradientEnd = Pink600
            )

            MoviePosterInfo(
                title = movieTitle,
                year = movieYear,
                duration = "2h 46min",
                rating = 8.5,
                posterUrl = "https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
                modifier = Modifier.offset(y = (-80).dp)
            )

            Spacer(modifier = Modifier.height((-64).dp))

            GenreChips(
                genres = listOf("Action", "Sci-Fi", "Aventure")
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButtons(
                isFavorite = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            SynopsisSection(
                synopsis = "Paul Atreides s'unit à Chani et aux Fremen pour mener la révolte contre ceux qui ont détruit sa famille. Hanté par de sombres prémonitions, il se trouve confronté au plus grand des dilemmes..."
            )

            Spacer(modifier = Modifier.height(24.dp))

            CastSection(cast = cast)

            Spacer(modifier = Modifier.height(24.dp))

            SimilarMoviesSection(movies = similarMovies)

            Spacer(modifier = Modifier.height(24.dp))

            ReviewsSection(reviews = reviews)

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(getMockMovies()[1])
}
