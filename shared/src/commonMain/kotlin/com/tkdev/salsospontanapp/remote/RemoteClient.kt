package com.tkdev.salsospontanapp.remote

interface RemoteClient {
    suspend fun fetchData(): RemoteDto
}
