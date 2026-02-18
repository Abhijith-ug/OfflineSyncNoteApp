package com.example.offlinesyncnoteapp.core

import com.example.offlinesyncnoteapp.core.worker.NoteSyncSchedular
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppFirebaseMessagingService: FirebaseMessagingService(){

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val type = message.data["type"]

        if (type == "SYNC_NOTES") {

            NoteSyncSchedular.scheduleOneTimeSync(
                applicationContext
            )

        }
    }
}