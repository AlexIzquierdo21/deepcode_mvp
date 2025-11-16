package com.deepcodeia.deepcode_app.ui.screens.mycreatedchallenges

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepcodeia.deepcode_app.domain.model.Challenge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.deepcodeia.deepcode_app.R

/**
 * Pantalla de mis retos creados (UI pura).
 * Muestra los retos creados por el usuario con opción de eliminar.
 */
@Composable
fun MyCreatedChallengesScreen(
    state: MyCreatedChallengesUiState,
    onDeleteChallenge: (Long) -> Unit,
    onBack: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header con botón de volver
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Mis Retos Creados",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(16.dp))

            // Lista de retos
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (state.challenges.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No has creado ningún reto aún",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.challenges) { challenge ->
                        MyChallengeCard(
                            challenge = challenge,
                            onDeleteClick = { onDeleteChallenge(challenge.id) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Card de un reto creado por el usuario con botón de eliminar.
 */
@Composable
private fun MyChallengeCard(
    challenge: Challenge,
    onDeleteClick: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header: Título + Botón eliminar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = challenge.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            // Descripción
            Text(
                text = challenge.description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(12.dp))

            // Tags de lenguaje y nivel
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Logo del lenguaje
                Image(
                    painter = painterResource(id = getLanguageLogo(challenge.programmingLanguage)),
                    contentDescription = getLanguageName(challenge.programmingLanguage),
                    modifier = Modifier.size(32.dp)
                )

                AssistChip(
                    onClick = {},
                    label = { Text(getLanguageName(challenge.programmingLanguage)) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
                AssistChip(
                    onClick = {},
                    label = { Text(getLevelName(challenge.level)) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            }
        }
    }

    // Diálogo de confirmación
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar reto") },
            text = { Text("¿Estás seguro de que quieres eliminar este reto?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDeleteClick()
                    }
                ) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

private fun getLanguageName(code: String): String {
    return when (code) {
        "PYTHON" -> "Python"
        "JAVA" -> "Java"
        "KOTLIN" -> "Kotlin"
        "HTML_CSS_JS" -> "HTML/CSS/JS"
        else -> code
    }
}

private fun getLevelName(code: String): String {
    return when (code) {
        "BEGINNER" -> "Principiante"
        "INTERMEDIATE" -> "Intermedio"
        else -> code
    }
}

/**
 * Obtiene el recurso drawable del logo según el lenguaje.
 */
@DrawableRes
private fun getLanguageLogo(code: String): Int {
    return when (code) {
        "PYTHON" -> R.drawable.ic_python
        "JAVA" -> R.drawable.ic_java
        "KOTLIN" -> R.drawable.ic_kotlin
        "HTML_CSS_JS" -> R.drawable.ic_web
        else -> R.drawable.ic_web
    }
}
