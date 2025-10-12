package com.deepcodeia.deepcode_app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Task
import androidx.compose.ui.unit.dp
import com.deepcodeia.deepcode_app.R
import androidx.compose.material3.MaterialTheme
import com.deepcodeia.deepcode_app.ui.theme.Dimens

@Composable
fun HomeScreen(
    onVideosClick: () -> Unit = {},
    onChallengesClick: () -> Unit = {},
    onCreateChallengeClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onLogout: () -> Unit = {},
) {
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(R.drawable.fondo_homescreen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0x99000000)) // 60% negro
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(max = 360.dp)
                    .padding(horizontal = Dimens.ScreenPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Menú",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MenuTile("Vídeos", Icons.Filled.PlayArrow, onVideosClick, Modifier.weight(1f))
                    MenuTile("Retos", Icons.Filled.Task, onChallengesClick, Modifier.weight(1f))
                }
                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MenuTile("Crear Reto", Icons.Filled.AddCircle, onCreateChallengeClick, Modifier.weight(1f))
                    MenuTile("Perfil", Icons.Filled.Person, onProfileClick, Modifier.weight(1f))
                }

                Spacer(Modifier.height(6.dp))
                Text("DeepCodeApp", color = MaterialTheme.colorScheme.secondary, fontSize = 18.sp)
            }
        }
    }
}
@Composable
private fun MenuTile(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(Dimens.TileRadius),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier
            .height(Dimens.TileHeight)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = label,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }
}



