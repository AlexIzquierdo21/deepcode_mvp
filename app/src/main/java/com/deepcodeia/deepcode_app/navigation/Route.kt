package com.deepcodeia.deepcode_app.navigation

sealed class Route(val route: String) {

    // Splash (pantalla inicial)
    data object Splash : Route("splash")

    // Auth flow
    data object Login : Route("login")
    data object Register : Route("register")

    // Home flow
    data object Home : Route("home")
    data object Videos : Route("videos")
    data object Profile : Route("profile")
    data object Challenges : Route("challenges")


    // TODO Futuras pantallas
    // data object CreateChallenge : Route("create_challenge")
}