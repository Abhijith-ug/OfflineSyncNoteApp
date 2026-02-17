package com.example.offlinesyncnoteapp.presentation.navigation

sealed class Destinations(val route : String) {
    object NoteList : Destinations("notes_list")

    object CreateNote : Destinations("create_note")
}