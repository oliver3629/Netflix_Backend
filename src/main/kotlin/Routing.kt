package com.laioffer

import com.laioffer.models.HomeResponse
import com.laioffer.models.ProfileResponse
import com.laioffer.models.VideoDetailRequest
import com.laioffer.models.VideoDetailResponse
import com.laioffer.services.ProfileService
import com.laioffer.services.VideoService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.http.content.staticResources
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        // Maps /media/... to src/main/resources/media/...
        staticResources("/media", "media")

        // Home: top recommended video + section rows
        get("/home") {
            try {
                val topRecommended = VideoService.getTopRecommended()
                val sections = VideoService.getSections()
                call.respond(HomeResponse(topRecommended, sections))
            } catch (e: Exception) {
                call.respondText("Error: ${e.message}\n${e.stackTraceToString()}", status = HttpStatusCode.InternalServerError)
            }
        }

        post("/videoDetail") {
            try {
                val request = call.receive<VideoDetailRequest>()

                val video = VideoService.getVideoById(request.id)
                if (video == null) {
                    call.respondText("Video not found", status = HttpStatusCode.NotFound)
                    return@post
                }

                ProfileService.addToRecentViewed(request.id)


                val episodes = if (video.type == "TV_SHOW") {
                    VideoService.getEpisodesByVideoId(request.id)
                } else {
                    null
                }

                call.respond(VideoDetailResponse(video, episodes))
            } catch (e: Exception) {
                call.respondText(
                    "Bad request: ${e.message ?: "Request failed"}",
                    status = HttpStatusCode.BadRequest
                )
            }
        }

        // Profile: user info + recently viewed videos
        get("/profile") {
            try {
                val profile = ProfileService.getProfile()
                val recentViewed = ProfileService.getRecentViewed()
                call.respond(ProfileResponse(profile, recentViewed))
            } catch (e: Exception) {
                call.respondText("Error: ${e.message}\n${e.stackTraceToString()}", status = HttpStatusCode.InternalServerError)
            }
        }


        get("/") {

            // Confirms this is our mock backend.
            call.respondText("Netflix Mock Backend API")

        }
    }
}
