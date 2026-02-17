package com.example.offlinesyncnoteapp.data.repository

import android.content.Context
import com.example.offlinesyncnoteapp.core.AppResult
import com.example.offlinesyncnoteapp.core.worker.NoteSyncSchedular
import com.example.offlinesyncnoteapp.data.local.dao.NoteDao
import com.example.offlinesyncnoteapp.data.local.mapper.toDomain
import com.example.offlinesyncnoteapp.data.local.mapper.toEntity
import com.example.offlinesyncnoteapp.domain.Note
import com.example.offlinesyncnoteapp.domain.SyncState
import com.example.offlinesyncnoteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.map
import java.util.UUID

class NoteRepositoryImpl(
    private val dao: NoteDao,
    private val context : Context
): NoteRepository {

    private val appContext = context.applicationContext

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
                syncState = SyncState.PENDING,
                retryCount = 0,
                lastRetryTime = System.currentTimeMillis()
            )

            dao.insert(note.toEntity())
            NoteSyncSchedular.scheduleOneTimeSync(appContext)

            AppResult.Success(Unit)

        } catch (e: Exception) {

            AppResult.Error("Failed to create note", e)

        }
    }

    override suspend fun getPendingNotes(): List<Note> {
        return dao.getPendingNotes().map { it.toDomain() }
    }

    override suspend fun updateSyncState(
        noteId: String,
        state: SyncState
    ) {
        dao.updateSyncState(noteId, state.name)
    }

    override  suspend fun updateNoteAfterFailure(note: Note) {

        val updated = note.copy(
            syncState = SyncState.FAILED,
            retryCount = note.retryCount + 1,
            lastRetryTime = System.currentTimeMillis()
        )

        dao.insert(updated.toEntity())
    }

  override  suspend fun getFailedNotes(): List<Note> {
        return dao.getFailedNotes().map { it.toDomain() }
    }
}