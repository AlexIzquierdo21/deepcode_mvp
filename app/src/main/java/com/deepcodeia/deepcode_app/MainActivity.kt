package com.deepcodeia.deepcode_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deepcodeia.deepcode_app.ui.HomeScreen
import com.deepcodeia.deepcode_app.ui.LoginScreen
import com.deepcodeia.deepcode_app.ui.VideosScreen
import com.deepcodeia.deepcode_app.ui.theme.DeepCode_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeepCode_AppTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "login") {
                    composable("login") {
                        LoginScreen(
                            onLogin = {
                                nav.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("home") {
                        HomeScreen(
                            onVideosClick = { nav.navigate("videos") },
                            onLogout = {
                                nav.navigate("login") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("videos") {
                        VideosScreen(
                            playlistId = "PLDkQmEYGZru8fgGR9JM7Lp-BSKD8Xz9-G",
                            secondaryPlaylistId = "PLDkQmEYGZru-z9VtbV89b-myzSymIGEvg",
                            onBack = { nav.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}