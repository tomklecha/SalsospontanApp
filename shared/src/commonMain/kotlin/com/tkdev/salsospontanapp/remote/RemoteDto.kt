package com.tkdev.salsospontanapp.remote

import kotlinx.serialization.Serializable

@Serializable
data class RemoteDto(
    val version: String? = null
)
