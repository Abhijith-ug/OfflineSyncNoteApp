package com.example.offlinesyncnoteapp.presentation.navigation.create_note

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.offlinesyncnoteapp.presentation.NotesViewmodel
import com.example.offlinesyncnoteapp.presentation.notes.NotesContract

@Composable
fun CreateNoteRoute(
    viewmodel: NotesViewmodel,
    onBack: () -> Unit,
    padding : PaddingValues
) {

    CreateNoteScreen(
        onSave = { title, content ->

            viewmodel.processIntent(
                NotesContract.UiIntent.CreateNote(title, content)
            )

            onBack()
        },
        onBack = onBack,
        padding
    )
    
}