package com.example.offlinesyncnoteapp.domain.repository

import com.example.offlinesyncnoteapp.core.AppResult
import com.example.offlinesyncnoteapp.data.local.mapper.toDomain
import com.example.offlinesyncnoteapp.domain.Note
import com.example.offlinesyncnoteapp.domain.SyncState
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun observeNotes(): Flow<List<Note>>

    suspend fun createNote(
        title: String,
        content: String
    ): AppResult<Unit>
    suspend fun getPendingNotes(): List<Note>

    suspend fun updateSyncState(
        noteId: String,
        state: SyncState
    )

    suspend fun updateNoteAfterFailure(note: Note)

    suspend fun getFailedNotes(): List<Note>


}