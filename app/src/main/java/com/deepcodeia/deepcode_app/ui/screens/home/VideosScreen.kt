package com.deepcodeia.deepcode_app.ui.screens.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.deepcodeia.deepcode_app.ui.components.AppButton
import com.deepcodeia.deepcode_app.ui.components.ButtonVariant

// Pantalla con accesos directos a las playlists de YouTube
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideosScreen(
    playlistId: String = "PLDkQmEYGZru8fgGR9JM7Lp-BSKD8Xz9-G",           // ID de la playlist principal
    secondaryPlaylistId: String = "PLDkQmEYGZru-z9VtbV89b-myzSymIGEvg",   // ID de la segunda playlist
    onBack: () -> Unit = {}                                                // Acción al pulsar "Volver"
) {
    val context = LocalContext.current   // Contexto actual (necesario para abrir URLs externas)

    // Estructura base con barra superior
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Vídeos",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    // Botón de volver atrás
                    TextButton(onClick = onBack) {
                        Text("← Volver", color = MaterialTheme.colorScheme.onBackground)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón que abre la playlist "Aprendiendo Python desde cero"
            AppButton(
                text = "Aprendiendo Python desde cero",
                onClick = {
                    val url = "https://www.youtube.com/playlist?list=$playlistId"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                },
                modifier = Modifier.fillMaxWidth(),
                variant = ButtonVariant.Primary,
                textColor = Color.Black
            )

            // Botón que abre la playlist "Estructuras de datos"
            AppButton(
                text = "Estructuras de datos",
                onClick = {
                    val url = "https://www.youtube.com/playlist?list=$secondaryPlaylistId"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                },
                modifier = Modifier.fillMaxWidth(),
                variant = ButtonVariant.Primary,
                textColor = Color.Black
            )
        }
    }
}



