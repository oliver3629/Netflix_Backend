package com.laioffer

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        // Maps /media/... to src/main/resources/media/...
        staticResources("/media", "media")

        get("/") {

            // Confirms this is our mock backend.
            call.respondText("Netflix Mock Backend API")

        }
    }
}