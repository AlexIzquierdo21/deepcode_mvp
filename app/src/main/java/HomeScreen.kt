package com.deepcodeia.deepcode_app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val DeepBg = Color(0xFF0B0B0B)
private val DeepSurface = Color(0xFF161616)
private val DeepNeon = Color(0xFF20FF00)
private val DeepBorder = Color(0xFF2E2E2E)
private val DeepTextSecondary = Color(0xFFBDBDBD)

@Composable
fun HomeScreen(
    onVideosClick: () -> Unit = {},
    onCoursesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Surface(Modifier.fillMaxSize(), color = DeepBg) {
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(max = 360.dp)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text(
                    "Menú",
                    color = DeepNeon,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MenuTile("Vídeos", Icons.Filled.PlayArrow, onVideosClick, Modifier.weight(1f))
                    MenuTile("Cursos", Icons.Filled.School, onCoursesClick, Modifier.weight(1f))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    MenuTile("Perfil", Icons.Filled.Person, onProfileClick, Modifier.weight(1f))
                    MenuTile("Ajustes", Icons.Filled.Settings, onSettingsClick, Modifier.weight(1f))
                }

                Spacer(Modifier.height(6.dp))
                Text("DeepCodeApp", color = DeepTextSecondary, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun MenuTile(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = DeepSurface,
        tonalElevation = 2.dp,
        border = BorderStroke(1.dp, DeepBorder),
        modifier = modifier
            .height(100.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = DeepNeon, modifier = Modifier.size(28.dp))
            Spacer(Modifier.height(8.dp))
            Text(label, fontWeight = FontWeight.SemiBold, color = Color.White)
        }
    }
}


