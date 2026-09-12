package com.laioffer.models

import kotlinx.serialization.Serializable

// Response body returned by GET /profile.
@Serializable
data class ProfileResponse(
    val profile: Profile,
    val recentViewed: List<Video>
)