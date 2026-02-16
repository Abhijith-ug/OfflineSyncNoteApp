package com.example.offlinesyncnoteapp.data.repository

import com.example.offlinesyncnoteapp.core.AppResult
import com.example.offlinesyncnoteapp.data.local.dao.NoteDao
import com.example.offlinesyncnoteapp.data.local.mapper.toDomain
import com.example.offlinesyncnoteapp.data.local.mapper.toEntity
import com.example.offlinesyncnoteapp.domain.Note
import com.example.offlinesyncnoteapp.domain.SyncState
import com.example.offlinesyncnoteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.map
import java.util.UUID

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {
    override fun observeNotes () = dao.observeNotes().map {
        list ->
        list.map {
            it.toDomain()
        }
    }

    override suspend fun createNote(
        title: String,
        content: String
    ): AppResult<Unit> {

        return try {

            val note = Note(
                id = UUID.randomUUID().toString(),
                title = title,
                content = content,
                createdAt = System.currentTimeMillis(),
                syncState = SyncState.PENDING
            )

            dao.insert(note.toEntity())

            AppResult.Success(Unit)

        } catch (e: Exception) {

            AppResult.Error("Failed to create note", e)

        }
    }
}