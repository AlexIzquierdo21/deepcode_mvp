package com.deepcodeia.deepcode_app.ui.screens.createchallenge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Pantalla de creación de retos (UI pura)
 * Formulario para crear nuevos retos de programación.
 */

@Composable
fun CreateChallengeScreen(
    state: CreateChallengeUiState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onLanguageSelected: (String) -> Unit,
    onLevelSelected: (String) -> Unit,
    onCreateClick: () -> Unit,
    onBack: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
                    text = "Crear Reto",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(24.dp))

            // Campo: Título
            OutlinedTextField(
                value = state.title,
                onValueChange = onTitleChange,
                label = { Text("Título del reto") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.titleError != null,
                supportingText = state.titleError?.let { { Text(it) } },
                singleLine = true
            )

            Spacer(Modifier.height(16.dp))

            // Campo: Descripción
            OutlinedTextField(
                value = state.description,
                onValueChange = onDescriptionChange,
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                isError = state.descriptionError != null,
                supportingText = state.descriptionError?.let { { Text(it) } },
                maxLines = 6
            )

            Spacer(Modifier.height(24.dp))

            // Selector: Lenguaje
            Text(
                text = "Lenguaje de programación:",
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

            Spacer(Modifier.height(24.dp))

            // Selecter: Nivel dificultad
            Text(
                text = "Nivel de dificultad:",
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
                        label = { Text("Intermediate") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // Botón de crear
            Button(
                onClick = onCreateClick,
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading && state.isValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(if (state.isLoading) "Creando..." else "Crear Reto")
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}















































