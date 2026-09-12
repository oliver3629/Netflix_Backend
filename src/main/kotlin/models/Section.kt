package com.laioffer.models

import kotlinx.serialization.Serializable

// Represents one horizontal row on the home screen.
@Serializable
data class Section(
    val title: String,
    val videos: List<Video>
)