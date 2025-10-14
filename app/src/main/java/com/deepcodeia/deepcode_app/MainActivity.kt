package com.deepcodeia.deepcode_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.deepcodeia.deepcode_app.navigation.AppNavGraph
import com.deepcodeia.deepcode_app.ui.theme.DeepCode_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeepCode_AppTheme {
                val nav = rememberNavController()
                AppNavGraph(nav)
            }
        }
    }
}
