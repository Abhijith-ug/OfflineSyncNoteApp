package com.example.offlinesyncnoteapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.offlinesyncnoteapp.presentation.NotesViewmodel
import com.example.offlinesyncnoteapp.presentation.navigation.create_note.CreateNoteRoute
import com.example.offlinesyncnoteapp.presentation.navigation.note_list.NotesRoute

@Composable
fun AppNavHost(
    navController : NavHostController,
    notesViewModel : NotesViewmodel,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.NoteList.route
    ){
        composable(Destinations.NoteList.route) {

            NotesRoute(
                viewModel = notesViewModel,
                onCreateClick = {
                    navController.navigate(Destinations.CreateNote.route)
                },
                padding = padding
            )

        }

        composable(Destinations.CreateNote.route) {

            CreateNoteRoute(
                viewmodel = notesViewModel,
                onBack = { navController.popBackStack() },
                padding
            )

        }
    }
    
}