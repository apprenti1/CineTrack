package fr.hainu.cinetrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.runtime.*
import fr.hainu.cinetrack.ui.screens.AuthChoiceScreen
import fr.hainu.cinetrack.ui.screens.OnboardingScreen
import fr.hainu.cinetrack.ui.screens.RegisterScreen
import fr.hainu.cinetrack.ui.screens.SplashScreen
import fr.hainu.cinetrack.ui.theme.CineTrackTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTrackTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("splash") }

    LaunchedEffect(Unit) {
        delay(2500)
        currentScreen = "onboarding"
    }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
        },
        label = "screen_transition"
    ) { screen ->
        when (screen) {
            "splash" -> SplashScreen()
            "onboarding" -> OnboardingScreen(
                onFinish = { currentScreen = "auth" }
            )
            "auth" -> AuthChoiceScreen(
                onLoginClick = { currentScreen = "login" },
                onRegisterClick = { currentScreen = "register" },
                onContinueWithoutAccount = { currentScreen = "" }
            )
            "register" -> RegisterScreen(
                onBackClick = { currentScreen = "auth" },
                onLoginClick = { currentScreen = "login" },
                onRegister = { currentScreen = "" }
            )
        }
    }
}