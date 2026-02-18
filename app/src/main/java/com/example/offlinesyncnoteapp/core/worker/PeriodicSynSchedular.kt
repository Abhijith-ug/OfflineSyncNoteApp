package com.example.offlinesyncnoteapp.core.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object PeriodicSynSchedular {
    fun schedulePeriodicSync(context: Context) {

        val request =
            PeriodicWorkRequestBuilder<NoteSyncWorker>(
                6, TimeUnit.HOURS
            )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "periodic_note_sync",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }

}