package com.eka.care.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.eka.care.task.navigation.SetupNavGraph
import com.eka.care.task.ui.theme.TaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskTheme {
                App()
            }
        }
    }

    @Composable
    fun App(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        SetupNavGraph(navController)
    }

}

