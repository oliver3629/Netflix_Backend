# Netflix Mock Backend

A lightweight mock backend for the Netflix Android app. It is built with Kotlin and Ktor and provides home-feed, video-detail, profile, and local media endpoints.

**Project Frontend:** [Netflix_Frontend](https://github.com/oliver3629/Netflix_Frontend) — the companion Android app that consumes this API.

## Features

- Home feed with a featured title and categorized video sections
- Movie and TV-show detail data, including TV episodes
- In-memory recently viewed history
- Mock profile data
- Static MP4 streaming from bundled resources
- JSON serialization with `kotlinx.serialization`
- Simulated network latency of 300–500 ms

## Tech Stack

- **Language:** Kotlin 2.2.20
- **Framework:** Ktor 3.3.1
- **Server engine:** Netty
- **Serialization:** kotlinx.serialization
- **Logging:** Logback 1.4.14
- **Build system:** Gradle 8.14

## Requirements

- JDK 17 or newer
- No external database or environment variables are required

## Run the Backend

1. Open the project in IntelliJ IDEA and wait for Gradle sync to finish.
2. Open `src/main/kotlin/Application.kt`.
3. Click the green run button next to `main()` and select **Run 'ApplicationKt'**.

The server listens on `0.0.0.0:8080`. Verify it at:

```text
http://localhost:8080/
```

Expected response:

```text
Netflix Mock Backend API
```

Alternatively, run the server from a terminal with `./gradlew run`, or `.\gradlew.bat run` on Windows.

## API Endpoints

- **`GET /`** — Health check and server identification
- **`GET /home`** — Featured video and categorized home sections
- **`POST /videoDetail`** — Video details and episodes for a supplied video ID
- **`GET /profile`** — Mock profile and recently viewed videos
- **`GET /media/{filename}`** — Bundled MP4 media files

Example video-detail request:

```bash
curl -X POST http://localhost:8080/videoDetail \
  -H "Content-Type: application/json" \
  -d '{"id":"squid-game"}'
```

Opening a video detail adds that video to the in-memory recently viewed list. This state resets whenever the server restarts.

## Build and Test

```bash
./gradlew build
./gradlew test
```

Use `gradlew.bat` instead of `./gradlew` on Windows.

## Project Structure

```text
src/main/kotlin/
├── Application.kt        # Server startup and Ktor configuration
├── Routing.kt            # HTTP routes
├── models/               # Serializable request and response models
└── services/             # In-memory video and profile data

src/main/resources/
├── logback.xml
└── media/                 # Sample MP4 files
```

<br><br>
