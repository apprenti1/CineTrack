package fr.hainu.cinetrack.ui.mock

import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.domain.models.ReviewModel
import fr.hainu.cinetrack.domain.models.CastMemberModel

fun getMockMovies(): List<MovieModel> {
    return listOf(
        MovieModel(
            id = 550,
            title = "Fight Club",
            rating = 8.4,
            posterUrl = "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg",
            year = "1999",
            genres = "Drame, Thriller",
            ratingCoef = 10,
            duration = "2h 19min",
            synopsis = "Un employé de bureau insomniaque et un vendeur de savon peu scrupuleux créent un club de combat underground qui évolue en quelque chose de bien plus grand.",
            trailerUrl = "https://www.youtube.com/watch?v=BdJKm16Co6M",
            cast = listOf(
                CastMemberModel(
                    name = "Brad Pitt",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/cckcYc2v0yh1tc9QjRelptcOBko.jpg"
                ),
                CastMemberModel(
                    name = "Edward Norton",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/5XBzD5WuTyVQZeS4VI25z2moMeY.jpg"
                ),
                CastMemberModel(
                    name = "Helena Bonham Carter",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/rHJmw5KNH3VYF9SOUv9BiF5yBmn.jpg"
                )
            ),
            reviews = listOf(
                ReviewModel(
                    id = 1,
                    comment = "Un chef-d'œuvre absolu ! La fin est incroyable.",
                    rating = 10,
                    refUser = "1",
                    userName = "CinePhile92",
                    refMovie = 550,
                    createdAt = "2024-01-15"
                ),
                ReviewModel(
                    id = 2,
                    comment = "Film culte, à voir absolument.",
                    rating = 9,
                    refUser = "2",
                    userName = "MovieLover",
                    refMovie = 550,
                    createdAt = "2024-02-10"
                )
            ),
            isOnFavorite = true,
            isOnWatchlist = false,
            isOnWatched = true,
            isRated = true
        ),
        MovieModel(
            id = 238,
            title = "The Godfather",
            rating = 8.7,
            posterUrl = "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            year = "1972",
            genres = "Drame, Crime",
            ratingCoef = 10,
            duration = "2h 55min",
            synopsis = "Le patriarche vieillissant d'une dynastie criminelle transfère le contrôle de son empire clandestin à son fils réticent.",
            trailerUrl = "https://www.youtube.com/watch?v=sY1S34973zA",
            cast = listOf(
                CastMemberModel(
                    name = "Marlon Brando",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/fuTEPWdksm8K7lTCGfeDcMGvZHF.jpg"
                ),
                CastMemberModel(
                    name = "Al Pacino",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/2dGBb1fOcNdZjtQToVPFxXjm4ke.jpg"
                ),
                CastMemberModel(
                    name = "James Caan",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/lCjYW4dlsDfn69n0MZWLDIh1YdG.jpg"
                )
            ),
            reviews = listOf(
                ReviewModel(
                    id = 3,
                    comment = "Le meilleur film de tous les temps.",
                    rating = 10,
                    refUser = "1",
                    userName = "CinePhile92",
                    refMovie = 238,
                    createdAt = "2024-01-20",
                )
            ),
            isOnFavorite = true,
            isOnWatchlist = true,
            isOnWatched = true,
            isRated = true
        ),
        MovieModel(
            id = 155,
            title = "The Dark Knight",
            rating = 9.0,
            posterUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            year = "2008",
            genres = "Action, Crime, Drame",
            ratingCoef = 10,
            duration = "2h 32min",
            synopsis = "Lorsque la menace connue sous le nom de Joker fait des ravages et du chaos sur les habitants de Gotham, Batman doit accepter l'un des plus grands tests psychologiques et physiques.",
            trailerUrl = "https://www.youtube.com/watch?v=EXeTwQWrcwY",
            cast = listOf(
                CastMemberModel(
                    name = "Christian Bale",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/vecCvAE92ISZLq6P4pUJBpZhbdB.jpg"
                ),
                CastMemberModel(
                    name = "Heath Ledger",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/3TSLKRGZkgb0q9Rck4aQKOlp3xb.jpg"
                ),
                CastMemberModel(
                    name = "Aaron Eckhart",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/yJtPukJjJK4gGaVWq9E9LN9xqsH.jpg"
                ),
                CastMemberModel(
                    name = "Michael Caine",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/bVZRMlpjTAO2pJK6v90buFgVbSW.jpg"
                )
            ),
            reviews = listOf(
                ReviewModel(
                    id = 4,
                    comment = "Heath Ledger est incroyable en Joker !",
                    rating = 10,
                    refUser = "2",
                    userName = "MovieLover",
                    refMovie = 155,
                    createdAt = "2024-03-01",
                ),
                ReviewModel(
                    id = 5,
                    comment = "Le meilleur film de super-héros jamais réalisé.",
                    rating = 9,
                    refUser = "3",
                    userName = "FilmCritic",
                    refMovie = 155,
                    createdAt = "2024-03-05",
                )
            ),
            isOnFavorite = false,
            isOnWatchlist = true,
            isOnWatched = false,
            isRated = false
        ),
        MovieModel(
            id = 13,
            title = "Forrest Gump",
            rating = 8.8,
            posterUrl = "https://image.tmdb.org/t/p/w500/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg",
            year = "1994",
            genres = "Comédie, Drame, Romance",
            ratingCoef = 10,
            duration = "2h 22min",
            synopsis = "Les présidences de Kennedy et Johnson, la guerre du Vietnam, le scandale du Watergate et d'autres événements historiques se déroulent du point de vue d'un homme de l'Alabama avec un QI de 75.",
            trailerUrl = "https://www.youtube.com/watch?v=bLvqoHBptjg",
            cast = listOf(
                CastMemberModel(
                    name = "Tom Hanks",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/xxCBjPWDQ0KIwvCwKzOxaKDdRvP.jpg"
                ),
                CastMemberModel(
                    name = "Robin Wright",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/aKMqDFTVIM0WDjx0KQHM4gkBJ1f.jpg"
                ),
                CastMemberModel(
                    name = "Gary Sinise",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/7G1lyPGWqX1m5SnOHNi7TYkYf3E.jpg"
                )
            ),
            reviews = listOf(
                ReviewModel(
                    id = 6,
                    comment = "Un film émouvant qui touche le cœur.",
                    rating = 9,
                    refUser = "1",
                    userName = "CinePhile92",
                    refMovie = 13,
                    createdAt = "2024-02-15",
                )
            ),
            isOnFavorite = true,
            isOnWatchlist = false,
            isOnWatched = true,
            isRated = true
        ),
        MovieModel(
            id = 680,
            title = "Pulp Fiction",
            rating = 8.9,
            posterUrl = "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg",
            year = "1994",
            genres = "Thriller, Crime",
            ratingCoef = 10,
            duration = "2h 34min",
            synopsis = "Les vies de deux tueurs à gages de la mafia, un boxeur, la femme d'un gangster et deux bandits s'entremêlent dans quatre histoires de violence et de rédemption.",
            trailerUrl = "https://www.youtube.com/watch?v=s7EdQ4FqbhY",
            cast = listOf(
                CastMemberModel(
                    name = "John Travolta",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/9GVufE87MMIrSn0CbJFLudkALdL.jpg"
                ),
                CastMemberModel(
                    name = "Samuel L. Jackson",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/AiAYAqwpM5xmiFrAIeQvUXDCVvo.jpg"
                ),
                CastMemberModel(
                    name = "Uma Thurman",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/xuxgPXyv6KjUHIM8cZaxx4ry25L.jpg"
                ),
                CastMemberModel(
                    name = "Bruce Willis",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/w3aXr1e7gQCn8MSp1vW4sXHn99P.jpg"
                )
            ),
            reviews = listOf(
                ReviewModel(
                    id = 7,
                    comment = "Tarantino à son meilleur. Dialogues brillants.",
                    rating = 10,
                    refUser = "2",
                    userName = "MovieLover",
                    refMovie = 680,
                    createdAt = "2024-01-25",
                ),
                ReviewModel(
                    id = 8,
                    comment = "Structure narrative géniale, casting parfait.",
                    rating = 9,
                    refUser = "3",
                    userName = "FilmCritic",
                    refMovie = 680,
                    createdAt = "2024-02-20",
                )
            ),
            isOnFavorite = false,
            isOnWatchlist = false,
            isOnWatched = true,
            isRated = true
        ),
        MovieModel(
            id = 424,
            title = "Schindler's List",
            rating = 8.9,
            posterUrl = "https://image.tmdb.org/t/p/w500/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg",
            year = "1993",
            genres = "Drame, Histoire, Guerre",
            ratingCoef = 10,
            duration = "3h 15min",
            synopsis = "En Pologne pendant la Seconde Guerre mondiale, Oskar Schindler devient progressivement préoccupé par sa main-d'œuvre juive après avoir été témoin de leur persécution par les nazis.",
            trailerUrl = "https://www.youtube.com/watch?v=gG22XNhtnoY",
            cast = listOf(
                CastMemberModel(
                    name = "Liam Neeson",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/bboldwqSC6tdw2iL6631c98l2Mn.jpg"
                ),
                CastMemberModel(
                    name = "Ralph Fiennes",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/tJr9n925vu2ZPx85OYiKsLcPYrx.jpg"
                ),
                CastMemberModel(
                    name = "Ben Kingsley",
                    profilePictureUrl = "https://image.tmdb.org/t/p/w185/vQtBqpF2HDdzbfXHDzR4u37i1Ac.jpg"
                )
            ),
            reviews = listOf(
                ReviewModel(
                    id = 9,
                    comment = "Bouleversant. Un film qui doit être vu par tous.",
                    rating = 10,
                    refUser = "1",
                    userName = "CinePhile92",
                    refMovie = 424,
                    createdAt = "2024-03-10",
                )
            ),
            isOnFavorite = false,
            isOnWatchlist = true,
            isOnWatched = false,
            isRated = false
        )
    )
}