package fr.hainu.cinetrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.hainu.cinetrack.ui.screens.AuthChoiceScreen
import fr.hainu.cinetrack.ui.screens.HomeScreen
import fr.hainu.cinetrack.ui.screens.LoginScreen
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
                    // TODO: Implémenter l'écran de récupération de mot de passe
                }
            )
        }

        composable(route = Destinations.HOME) {
            HomeScreen()
        }
    }
}
