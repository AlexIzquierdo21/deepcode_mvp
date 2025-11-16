package com.deepcodeia.deepcode_app.ui.screens.challenges

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepcodeia.deepcode_app.R
import com.deepcodeia.deepcode_app.domain.model.Challenge
import com.deepcodeia.deepcode_app.ui.screens.challenges.CompletionFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image


/**
 * Pantalla de lista de retos (UI pura).
 * Muestra retos con filtros por lenguaje y nivel.
 */
@Composable
fun ChallengesScreen(
    state: ChallengesUiState,
    onLanguageSelected: (String?) -> Unit,
    onLevelSelected: (String?) -> Unit,
    onCompletionFilterSelected: (CompletionFilter) -> Unit,
    onChallengeClick: (Challenge) -> Unit,
    onMarkAsCompleted: (Long) -> Unit,
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
                    text = "Retos de Programación",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(16.dp))

            // Filtros de lenguaje
            Text(
                text = "Lenguaje:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = state.selectedLanguage == null,
                        onClick = { onLanguageSelected(null) },
                        label = { Text("Todos") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = state.selectedLanguage == "PYTHON",
                        onClick = { onLanguageSelected("PYTHON") },
                        label = { Text("Python") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = state.selectedLanguage == "JAVA",
                        onClick = { onLanguageSelected("JAVA") },
                        label = { Text("Java") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = state.selectedLanguage == "KOTLIN",
                        onClick = { onLanguageSelected("KOTLIN") },
                        label = { Text("Kotlin") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = state.selectedLanguage == "HTML_CSS_JS",
                        onClick = { onLanguageSelected("HTML_CSS_JS") },
                        label = { Text("HTML/CSS/JS") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Filtros de nivel
            Text(
                text = "Nivel:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = state.selectedLevel == null,
                        onClick = { onLevelSelected(null) },
                        label = { Text("Todos") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = state.selectedLevel == "BEGINNER",
                        onClick = { onLevelSelected("BEGINNER") },
                        label = { Text("Principiante") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = state.selectedLevel == "INTERMEDIATE",
                        onClick = { onLevelSelected("INTERMEDIATE") },
                        label = { Text("Intermedio") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Spacer(Modifier.height(16.dp))

            // Filtro de completados
            Text(
                text = "Estado:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = state.selectedCompletionFilter == CompletionFilter.ALL,
                        onClick = { onCompletionFilterSelected(CompletionFilter.ALL) },
                        label = { Text("Todos") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = state.selectedCompletionFilter == CompletionFilter.COMPLETED,
                        onClick = { onCompletionFilterSelected(CompletionFilter.COMPLETED) },
                        label = { Text("Completados") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = state.selectedCompletionFilter == CompletionFilter.NOT_COMPLETED,
                        onClick = { onCompletionFilterSelected(CompletionFilter.NOT_COMPLETED) },
                        label = { Text("No Completados") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
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
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.filteredChallenges) { challenge ->
                        ChallengeCard(
                            challenge = challenge,
                            isCompleted = state.isChallengeCompleted(challenge.id),
                            onClick = { onChallengeClick(challenge) },
                            onMarkAsCompleted = { onMarkAsCompleted(challenge.id) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Card individual de un reto.
 */
@Composable
private fun ChallengeCard(
    challenge: Challenge,
    isCompleted: Boolean,
    onClick: () -> Unit,
    onMarkAsCompleted: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) {
                MaterialTheme.colorScheme.onSurfaceVariant
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Título
            Text(
                text = challenge.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(8.dp))

            // Descripción
            Text(
                text = challenge.description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(12.dp))

            // Tags de lenguaje, nivel y botón completar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo del lenguaje
                Image(
                    painter = painterResource(id = getLanguageLogo(challenge.programmingLanguage)),
                    contentDescription = getLanguageName(challenge.programmingLanguage),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(8.dp))

                AssistChip(
                    onClick = {},
                    label = { Text(getLanguageName(challenge.programmingLanguage)) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
                Spacer(Modifier.width(8.dp))

                AssistChip(
                    onClick = {},
                    label = { Text(getLevelName(challenge.level)) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                )

                Spacer(Modifier.weight(1f))

                if (!isCompleted) {
                    IconButton(
                        onClick = onMarkAsCompleted,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Marcar como completado",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Convierte el código del lenguaje a nombre legible.
 */
private fun getLanguageName(code: String): String {
    return when (code) {
        "PYTHON" -> "Python"
        "JAVA" -> "Java"
        "KOTLIN" -> "Kotlin"
        "HTML_CSS_JS" -> "HTML/CSS/JS"
        else -> code
    }
}

/**
 * Convierte el código del nivel a nombre legible.
 */
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