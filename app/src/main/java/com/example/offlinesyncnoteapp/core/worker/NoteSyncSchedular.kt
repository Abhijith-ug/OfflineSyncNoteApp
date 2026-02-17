package com.example.offlinesyncnoteapp.core.worker

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

object NoteSyncSchedular {
    fun scheduleOneTimeSync(context: Context) {

        val request =
            OneTimeWorkRequestBuilder<NoteSyncWorker>()
                .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                "note_sync",
                ExistingWorkPolicy.KEEP,
                request
            )
    }

}