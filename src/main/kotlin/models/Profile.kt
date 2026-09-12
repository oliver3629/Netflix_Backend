package com.laioffer.models

import kotlinx.serialization.Serializable

// Represents the current mock user.
@Serializable
data class Profile(
    val id: String,
    val name: String,
    val avatarUrl: String
)