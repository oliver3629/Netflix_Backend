package com.laioffer.services

import com.laioffer.models.Profile
import com.laioffer.models.Video
import java.util.*

/** In-memory profile and recently viewed list. Thread-safe for concurrent requests. */
object ProfileService {
    private val profile = Profile(
        id = "1",
        name = "Tim",
        avatarUrl = "https://ui-avatars.com/api/?name=Tim&background=0066cc&color=fff"
    )

    private val recentViewedIds = Collections.synchronizedList(mutableListOf<String>()) // Track video IDs (most recent first)

    fun getProfile(): Profile {
        return profile
    }

    fun addToRecentViewed(videoId: String) {
        // Remove if exists to avoid duplicates
        recentViewedIds.remove(videoId)
        // Add to front (most recent first)
        recentViewedIds.add(0, videoId)
        // Keep only last 5 items
        if (recentViewedIds.size > 5) {
            recentViewedIds.removeLast()
        }
    }

    fun getRecentViewed(): List<Video> {
        // Return actual Video objects from VideoService
        return recentViewedIds.mapNotNull { VideoService.getVideoById(it) }
    }
}