package com.example.offlinesyncnoteapp.core.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.offlinesyncnoteapp.core.RetryPolicy
import com.example.offlinesyncnoteapp.core.di.AppContainer
import com.example.offlinesyncnoteapp.data.remote.FakeNotesApi
import com.example.offlinesyncnoteapp.domain.SyncState

class NoteSyncWorker(
    context: Context,
    params: WorkerParameters
): CoroutineWorker(context,params) {
    override suspend fun doWork(): Result {

        val repo = AppContainer.noteRepository
        val api = FakeNotesApi()

        return try {

            val pending = repo.getPendingNotes()
            val failed = repo.getFailedNotes()
            val notesToSync = pending + failed
            notesToSync.forEach { note ->

                try {
                    val now = System.currentTimeMillis()

                    val delay = RetryPolicy.getRetryDelayMillis(note.retryCount)

                    val readyToRetry =
                        note.lastRetryTime == 0L ||
                                now - note.lastRetryTime >= delay

                    if (!readyToRetry) return@forEach
                    api.uploadNote(note)

                    repo.updateSyncState(
                        note.id,
                        SyncState.SYNCED
                    )

                } catch (e: Exception) {

                    repo.updateSyncState(
                        note.id,
                        SyncState.FAILED
                    )

                }

            }

            Result.success()

        } catch (e: Exception) {

            Result.retry()

        }

    }

}