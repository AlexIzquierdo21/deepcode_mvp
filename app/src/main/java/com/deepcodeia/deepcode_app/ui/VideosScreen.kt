package com.deepcodeia.deepcode_app.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


private val DeepBg = Color(0xFF0B0B0B)
private val DeepNeon = Color(0xFF20FF00)

@Composable
fun VideosScreen(
    playlistId: String = "PLDkQmEYGZru8fgGR9JM7Lp-BSKD8Xz9-G",
    secondaryPlaylistId: String = "PLDkQmEYGZru-z9VtbV89b-myzSymIGEvg",
    onBack: () -> Unit = {}
)

{
    val context = LocalContext.current
    Surface(color = DeepBg, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Text("← Volver", color = Color.White)
            }

            Text("Vídeos", color = DeepNeon, fontWeight = FontWeight.Bold)

            Button(
                onClick = {
                    val url = "https://www.youtube.com/playlist?list=$playlistId"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DeepNeon,
                    contentColor = Color.Black
                )
            ) {
                Text("Aprendiendo Python desde cero", fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    val url = "https://www.youtube.com/playlist?list=$secondaryPlaylistId"
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DeepNeon,
                    contentColor = Color.Black
                )
            ) {
                Text("Estructuras de datos", fontWeight = FontWeight.SemiBold)
            }


        }
    }
}


