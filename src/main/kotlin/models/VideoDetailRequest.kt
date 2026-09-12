package com.laioffer.models

import kotlinx.serialization.Serializable

// Request body sent by Android when a user opens a video detail page.
@Serializable
data class VideoDetailRequest(
    val id: String
)