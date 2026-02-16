package com.example.offlinesyncnoteapp.domain

data class Note(
    val id: String,
    val title : String,
    val content : String,
    val createdAt : Long,
    val syncState : SyncState
)
