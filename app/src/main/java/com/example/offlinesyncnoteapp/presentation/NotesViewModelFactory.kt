package com.example.offlinesyncnoteapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.offlinesyncnoteapp.domain.repository.NoteRepository

class NotesViewModelFactory(
    private val repository: NoteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(NotesViewmodel::class.java)) {


            return NotesViewmodel(
                repository = repository,
            ) as T

        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}