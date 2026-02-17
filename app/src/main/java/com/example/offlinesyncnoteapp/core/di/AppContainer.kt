package com.example.offlinesyncnoteapp.core.di

import com.example.offlinesyncnoteapp.domain.repository.NoteRepository

object AppContainer {
    lateinit var noteRepository: NoteRepository
}