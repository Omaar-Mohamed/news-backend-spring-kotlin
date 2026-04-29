package com.example.sciencenewsapi.dto.guardian

data class SyncNewsResponse(
    val message: String,
    val addedCount: Int,
    val skippedCount: Int
)