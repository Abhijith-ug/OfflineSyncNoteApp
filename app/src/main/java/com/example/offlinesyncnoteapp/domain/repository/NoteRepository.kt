package com.example.offlinesyncnoteapp.domain.repository

import com.example.offlinesyncnoteapp.core.AppResult
import com.example.offlinesyncnoteapp.domain.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun observeNotes(): Flow<List<Note>>

    suspend fun createNote(
        title: String,
        content: String
    ): AppResult<Unit>

}