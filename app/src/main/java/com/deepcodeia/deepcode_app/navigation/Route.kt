package com.deepcodeia.deepcode_app.navigation

sealed class Route(val route: String) {

    // Auth flow
    data object Login : Route("login")

    // Home flow
    data object Home : Route("home")
    data object Videos : Route("videos")

    //FUTURAS PANTALLAS (descomentar cuando estén creadas).
    //data object Challenges : Route("challenges")
    //data object CreateChallenge : Route("create_challenges")
    //data object Profile : Route("profile")
}