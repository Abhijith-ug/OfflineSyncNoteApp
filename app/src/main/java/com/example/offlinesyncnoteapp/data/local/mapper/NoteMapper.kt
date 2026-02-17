package com.example.offlinesyncnoteapp.data.local.mapper

import com.example.offlinesyncnoteapp.data.local.entity.NoteEntity
import com.example.offlinesyncnoteapp.domain.Note
import com.example.offlinesyncnoteapp.domain.SyncState

fun NoteEntity.toDomain(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        syncState = SyncState.valueOf(syncState),
        retryCount = retryCount,
        lastRetryTime = lastRetryTime

    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        syncState = syncState.name,
        retryCount = retryCount,
        lastRetryTime = lastRetryTime
    )
}