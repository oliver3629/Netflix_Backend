package com.laioffer

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlin.random.Random

/** Entry point. Starts the mock API server on port 8080. */
fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    // Installs JSON conversion for Kotlin response objects.
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    // Adds random fake network latency to every backend request.
    intercept(ApplicationCallPipeline.Setup) {
        delay(Random.nextLong(300, 501))
    }

    configureRouting()
}