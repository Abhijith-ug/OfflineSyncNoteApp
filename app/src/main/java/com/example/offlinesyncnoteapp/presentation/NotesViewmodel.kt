package com.example.offlinesyncnoteapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlinesyncnoteapp.core.AppResult
import com.example.offlinesyncnoteapp.domain.repository.NoteRepository
import com.example.offlinesyncnoteapp.presentation.notes.NotesContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewmodel(
    private val repository: NoteRepository,
): ViewModel() {

    private val _state = MutableStateFlow(NotesContract.UiState())
    val state = _state.asStateFlow()

    fun processIntent(intent: NotesContract.UiIntent) {

        when (intent) {

            is NotesContract.UiIntent.CreateNote -> createNote(intent)

            NotesContract.UiIntent.LoadNotes -> observeNotes()

        }

    }


    private fun observeNotes() {

        viewModelScope.launch {

            repository.observeNotes().collect { notes ->

                _state.update {
                    it.copy(notes = notes)
                }

            }

        }

    }

    private fun createNote(intent: NotesContract.UiIntent.CreateNote) {

        viewModelScope.launch {

            when (val result =
                repository.createNote(intent.title, intent.content)
            ) {

                is AppResult.Success -> Unit

                is AppResult.Error -> {

                    _state.update {
                        it.copy(error = result.message)
                    }

                }
            }

        }

    }
}