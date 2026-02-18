package com.example.offlinesyncnoteapp.core.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

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