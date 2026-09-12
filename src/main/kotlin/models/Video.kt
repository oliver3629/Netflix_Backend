package com.laioffer.models

import kotlinx.serialization.Serializable

// Represents one movie or TV show card in the Netflix UI.
@Serializable
data class Video(
    val id: String,
    val title: String,
    val type: String,
    val year: String,
    val rating: String,
    val description: String,
    val posterUrl: String,
    val videoUrl: String,
    val duration: String? = null
)