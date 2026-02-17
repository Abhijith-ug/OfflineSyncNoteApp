package com.example.offlinesyncnoteapp.core

object RetryPolicy {
    private const val BASE_DELAY = 3000L

    fun getRetryDelayMillis(retryCount:Int): Long{
        return BASE_DELAY* (1 shl  retryCount)
    }
}