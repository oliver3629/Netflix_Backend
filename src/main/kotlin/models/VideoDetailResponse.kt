package com.laioffer.models

import kotlinx.serialization.Serializable

// Response body returned by POST /videoDetail.
@Serializable
data class VideoDetailResponse(
    val video: Video,
    val episodes: List<Episode>? = null
)