package fr.hainu.cinetrack.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import fr.hainu.cinetrack.domain.models.MovieModel
import fr.hainu.cinetrack.ui.screens.AuthChoiceScreen
import fr.hainu.cinetrack.ui.screens.LoginScreen
import fr.hainu.cinetrack.ui.screens.MainScreen
import fr.hainu.cinetrack.ui.screens.MovieDetailsScreen
import fr.hainu.cinetrack.ui.screens.OnboardingScreen
import fr.hainu.cinetrack.ui.screens.RegisterScreen
import fr.hainu.cinetrack.ui.screens.SplashScreen

object Destinations {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val AUTH = "auth"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val MOVIE_DETAILS = "movie_details/{movieJson}"

    fun movieDetails(movie: MovieModel): String {
        val json = Gson().toJson(movie)
        return "movie_details/${Uri.encode(json)}"
    }
}


@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.SPLASH
    ) {
        composable(route = Destinations.SPLASH) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(Destinations.ONBOARDING) {
                        popUpTo(Destinations.SPLASH) { inclusive = true }
                    }
                },
                onNavigateToAuth = {
                    navController.navigate(Destinations.AUTH) {
                        popUpTo(Destinations.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Destinations.ONBOARDING) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Destinations.AUTH) {
                        popUpTo(Destinations.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Destinations.AUTH) {
            AuthChoiceScreen(
                onLoginClick = {
                    navController.navigate(Destinations.LOGIN)
                },
                onRegisterClick = {
                    navController.navigate(Destinations.REGISTER)
                },
                onContinueWithoutAccount = {
                    navController.navigate(Destinations.HOME) {
                        popUpTo(Destinations.AUTH) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Destinations.REGISTER) {
            RegisterScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLoginClick = {
                    navController.navigate(Destinations.LOGIN) {
                        popUpTo(Destinations.AUTH)
                    }
                },
                onRegister = {
                    navController.navigate(Destinations.HOME) {
                        popUpTo(Destinations.AUTH) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Destinations.LOGIN) {
            LoginScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLogin = {
                    navController.navigate(Destinations.HOME) {
                        popUpTo(Destinations.AUTH) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Destinations.REGISTER) {
                        popUpTo(Destinations.AUTH)
                    }
                },
                onForgotPasswordClick = {
                    // TODO: mot de passe oublié
                }
            )
        }

        composable(route = Destinations.HOME) {
            MainScreen(
                onMovieClick = { movie ->
                    navController.navigate(Destinations.movieDetails(movie))
                }
            )
        }

        composable(
            route = Destinations.MOVIE_DETAILS,
            arguments = listOf(navArgument("movieJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movieJson")
            val movie = movieJson?.let { Gson().fromJson(Uri.decode(it), MovieModel::class.java) }

            movie?.let {
                MovieDetailsScreen(
                    movie = it,
                    onBackClick = {
                        navController.popBackStack(route = Destinations.HOME, inclusive = false)
                    },
                    onRateClick = {
                        // TODO: noter le film
                    }
                )
            }
        }
    }
}
