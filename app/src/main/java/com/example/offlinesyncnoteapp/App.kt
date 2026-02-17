package com.example.offlinesyncnoteapp

import android.app.Application
import com.example.offlinesyncnoteapp.core.di.AppContainer
import com.example.offlinesyncnoteapp.data.local.DatabaseProvider
import com.example.offlinesyncnoteapp.data.repository.NoteRepositoryImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val db = DatabaseProvider.getDatabase(this)

        AppContainer.noteRepository =
            NoteRepositoryImpl(db.noteDao(),this)
    }
}