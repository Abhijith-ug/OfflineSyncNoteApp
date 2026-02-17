package com.example.offlinesyncnoteapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.offlinesyncnoteapp.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    fun observeNotes(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE syncState = 'PENDING'")
    suspend fun getPendingNotes(): List<NoteEntity>

    @Query("UPDATE notes SET syncState = :state WHERE id = :noteId")
    suspend fun updateSyncState(noteId: String, state: String)

    @Query("SELECT * FROM notes WHERE syncState = 'FAILED'")
    suspend fun getFailedNotes(): List<NoteEntity>

}