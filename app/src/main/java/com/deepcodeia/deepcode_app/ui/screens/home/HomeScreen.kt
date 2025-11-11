package com.deepcodeia.deepcode_app.ui.screens.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepcodeia.deepcode_app.R
import com.deepcodeia.deepcode_app.ui.theme.Dimens

// Pantalla principal de la app con el menú de navegación
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview (ShowBackground = True)
fun HomeScreen(
    onVideosClick: () -> Unit = {},           // Acción al pulsar "Vídeos"
    onChallengesClick: () -> Unit = {},       // Acción al pulsar "Retos"
    onCreateChallengeClick: () -> Unit = {},  // Acción al pulsar "Crear Reto"
    onProfileClick: () -> Unit = {},          // Acción al pulsar "Perfil"
    onLogout: () -> Unit = {},                // Acción al cerrar sesión
) {
    // Estructura general con barra superior
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Menú",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        // Contenedor principal
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Imagen de fondo
            Image(
                painter = painterResource(R.drawable.fondo_homescreen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Capa oscura para mejorar la legibilidad del contenido
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0x99000000)) // 60% negro
            )

            // Contenido principal centrado
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(max = 360.dp)
                    .padding(horizontal = Dimens.ScreenPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Fila 1: Vídeos y Retos
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MenuTile("Vídeos", Icons.Filled.PlayArrow,
                        onVideosClick, Modifier.weight(1f))
                    MenuTile("Retos", Icons.Filled.Task,
                        onChallengesClick, Modifier.weight(1f))
                }

                Spacer(Modifier.height(16.dp))

                // Fila 2: Crear Reto y Perfil
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MenuTile("Crear Reto", Icons.Filled.AddCircle,
                        onCreateChallengeClick, Modifier.weight(1f))
                    MenuTile("Perfil", Icons.Filled.Person,
                        onProfileClick, Modifier.weight(1f))
                }

                Spacer(Modifier.height(6.dp))
                // Texto inferior con nombre de la app
                Text(
                    "DeepCodeApp",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        shadow = Shadow(
                            color = MaterialTheme.colorScheme.secondary,
                            offset = Offset(0f, 0f),
                            blurRadius = 25f
                        )
                    )
                )
            }
        }
    }
}

// Componente reutilizable para cada opción del menú
@Composable
private fun MenuTile(
    label: String,               // Texto del botón
    icon: ImageVector,           // Icono mostrado
    onClick: () -> Unit,         // Acción al pulsar
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(Dimens.TileRadius),   // Bordes redondeados
        color = MaterialTheme.colorScheme.surface,       // Color del fondo del tile
        tonalElevation = 2.dp,                           // Elevación (sombra)
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline), // Borde gris
        modifier = modifier
            .height(Dimens.TileHeight)
            .clickable { onClick() }        // Acción al hacer click
    ) {
        // Contenido del tile (icono + texto)
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




