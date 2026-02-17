package com.example.offlinesyncnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.offlinesyncnoteapp.presentation.navigation.AppNavHost
import com.example.offlinesyncnoteapp.presentation.provideNotesViewModel
import com.example.offlinesyncnoteapp.ui.theme.OfflineSyncNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = provideNotesViewModel()

        setContent {

            val navController = rememberNavController()
             Scaffold(
                 modifier = Modifier.fillMaxSize()
             ) { innerPadding ->
                 AppNavHost(
                     navController = navController,
                     notesViewModel = viewModel,
                     padding = innerPadding
                 )
             }


        }
    }
}

