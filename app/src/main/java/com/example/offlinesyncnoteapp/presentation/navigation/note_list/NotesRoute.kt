package com.example.offlinesyncnoteapp.presentation.navigation.note_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.offlinesyncnoteapp.presentation.NotesViewmodel

@Composable
fun NotesRoute(
    viewModel: NotesViewmodel,
    onCreateClick: () -> Unit,
    padding: PaddingValues
) {

    val state by viewModel.state.collectAsState()
    


    NotesScreen(
        state = state,
        onCreateClick = onCreateClick,
        padding
    )
    
}