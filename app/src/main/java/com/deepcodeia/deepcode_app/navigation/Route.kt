package com.deepcodeia.deepcode_app.navigation

sealed class Route(val route: String) {

    // Auth flow
    data object Login : Route("login")
    data object Register : Route("register")

    // Home flow
    data object Home : Route("home")
    data object Videos : Route("videos")

    // Futuras pantallas
    // data object Challenges : Route("challenges")
    // data object CreateChallenge : Route("create_challenge")
    // data object Profile : Route("profile")
}