package com.example.offlinesyncnoteapp.presentation

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.offlinesyncnoteapp.core.di.AppContainer

fun ComponentActivity.provideNotesViewModel(): NotesViewmodel {

    val factory = NotesViewModelFactory(
        AppContainer.noteRepository
    )

    return ViewModelProvider(
        this,
        factory
    )[NotesViewmodel::class.java]

}