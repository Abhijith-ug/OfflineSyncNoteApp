package com.example.offlinesyncnoteapp.presentation.notes

import com.example.offlinesyncnoteapp.domain.Note

object NotesContract {
    data class UiState(
        val notes : List<Note> = emptyList(),
        val isLoading : Boolean = false,
        val error : String ? = null
    )

    sealed class UiIntent {
        data class CreateNote(
            val title:String ,
            val content : String
        ):UiIntent()

        object LoadNotes : UiIntent()
    }


}