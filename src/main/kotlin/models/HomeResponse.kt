package com.laioffer.models

import kotlinx.serialization.Serializable

// Response body returned by GET /home.
@Serializable
data class HomeResponse(
    val topRecommended: Video?,
    val sections: List<Section>
)