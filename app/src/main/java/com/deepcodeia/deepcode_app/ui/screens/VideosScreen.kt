package com.deepcodeia.deepcode_app.ui.screens

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideosScreen(
    playlistId: String = "PLDkQmEYGZru8fgGR9JM7Lp-BSKD8Xz9-G",
    secondaryPlaylistId: String = "PLDkQmEYGZru-z9VtbV89b-myzSymIGEvg",
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vídeos", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold) },
                navigationIcon = {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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


