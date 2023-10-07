package com.sunit.news.sync

interface Syncable {
    suspend fun sync(): Boolean
}
