package com.deepcodeia.deepcode_app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepcodeia.deepcode_app.R
import androidx.compose.material3.Divider



private val DeepBg = Color(0xFF0B0B0B)
private val DeepSurface = Color(0xFF161616)
private val DeepNeon = Color(0xFF20FF00)
private val DeepText = Color(0xFFEFEFEF)
private val DeepTextSecondary = Color(0xFFBDBDBD)
private val DeepBorder = Color(0xFF2E2E2E)

@Composable
fun LoginScreen(onLogin: () -> Unit = {}){
    Surface(modifier = Modifier.fillMaxSize(), color = DeepBg) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)) {

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(max = 360.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_sinfondo),
                    contentDescription = "Logo DeepCodeApp",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(220.dp)
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = "DeepCodeApp",
                    color = DeepNeon,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(28.dp))

                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Correo electrónico") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DeepNeon,
                        focusedLabelColor = DeepNeon,
                        focusedLeadingIconColor = DeepNeon,
                        unfocusedContainerColor = DeepSurface,
                        focusedContainerColor = DeepSurface,
                        unfocusedBorderColor = DeepBorder
                    )
                )

                Spacer(Modifier.height(14.dp))

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Contraseña") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DeepNeon,
                        focusedLabelColor = DeepNeon,
                        focusedLeadingIconColor = DeepNeon,
                        unfocusedContainerColor = DeepSurface,
                        focusedContainerColor = DeepSurface,
                        unfocusedBorderColor = DeepBorder
                    )
                )

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = onLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepNeon,
                        contentColor = Color.Black
                    )
                ) { Text("Iniciar sesión", fontWeight = FontWeight.SemiBold) }

                Spacer(Modifier.height(18.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(Modifier.weight(1f), color = DeepBorder)
                    Text("  O inicia sesión con  ", color = DeepTextSecondary, fontSize = 13.sp)
                    Divider(Modifier.weight(1f), color = DeepBorder)
                }

                Spacer(Modifier.height(14.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SocialCircle(iconRes = R.drawable.ic_google)
                    SocialCircle(iconRes = R.drawable.ic_facebook)
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    text = "¿No tienes cuenta? Regístrate aquí",
                    color = DeepTextSecondary,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun SocialCircle(iconRes: Int) {
    // Círculo más compacto con leve elevación
    Surface(
        shape = CircleShape,
        color = DeepSurface,
        tonalElevation = 2.dp,
        shadowElevation = 6.dp // leve sombra
    ) {
        Box(
            modifier = Modifier
                .size(56.dp) // antes 64: se ve más proporcionado
                .border(1.dp, DeepBorder, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
