package com.laioffer.models

import kotlinx.serialization.Serializable

// Represents one episode for a TV show detail page.
@Serializable
data class Episode(
    val id: String,
    val title: String,
    val episodeNumber: Int,
    val duration: String,
    val description: String,
    val videoUrl: String,
    val thumbnailUrl: String
)