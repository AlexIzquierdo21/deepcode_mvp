package com.deepcodeia.deepcode_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.deepcodeia.deepcode_app.ui.theme.DeepCode_AppTheme
import com.deepcodeia.deepcode_app.ui.LoginScreen
import com.deepcodeia.deepcode_app.ui.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeepCode_AppTheme {
                LoginScreen()
            }
        }
    }
}