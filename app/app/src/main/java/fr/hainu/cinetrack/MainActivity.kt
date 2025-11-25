package fr.hainu.cinetrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import fr.hainu.cinetrack.navigation.NavGraph
import fr.hainu.cinetrack.ui.theme.CineTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineTrackTheme {
                NavGraph()
            }
        }
    }
}