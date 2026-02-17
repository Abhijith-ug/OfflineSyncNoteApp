package com.example.offlinesyncnoteapp.data.remote

import com.example.offlinesyncnoteapp.domain.Note
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakeNotesApi {

    suspend fun uploadNote(note: Note){
        delay(1500)
        if (Random.nextInt(10)<3){
            throw Exception("Failed to upload note")

        }
    }
}