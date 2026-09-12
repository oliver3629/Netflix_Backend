package com.laioffer.services

import com.laioffer.models.Video
import com.laioffer.models.Episode
import com.laioffer.models.Section
import java.util.Collections

/** In-memory catalog: videos, episodes, and home sections. Thread-safe. */
object VideoService {
    private val videos = Collections.synchronizedList(mutableListOf<Video>())
    private val episodes = Collections.synchronizedMap(mutableMapOf<String, List<Episode>>()) // videoId -> episodes
    private var topRecommendedId: String = "squid-game"

    init {
        initializeVideos()
    }

    fun getTopRecommended(): Video? {
        return videos.find { it.id == topRecommendedId }
    }

    fun getSections(): List<Section> {
        return listOf(
            Section("Trending Now", listOf("squid-game", "the-100", "kpop-demon-hunters").mapNotNull { id -> videos.find { it.id == id } }),
            Section("TV Dramas", listOf("squid-game", "the-100", "the-avengers", "wednesday-main").mapNotNull { id -> videos.find { it.id == id } }),
            Section("Action & Adventure", listOf("squid-game", "the-100", "kpop-demon-hunters").mapNotNull { id -> videos.find { it.id == id } }),
            Section("Sci-Fi", listOf("the-100").mapNotNull { id -> videos.find { it.id == id } })
        )
    }

    fun getVideoById(id: String): Video? {
        return videos.find { it.id == id }
    }

    fun getEpisodesByVideoId(videoId: String): List<Episode>? {
        return episodes[videoId]
    }

    private fun initializeVideos() {
        // Using real MP4 test videos that work on Android
        // Big Buck Bunny and other sample videos from public CDNs

        val bigBuckBunnyUrl = "http://10.0.2.2:8080/media/big-buck-bunny.mp4"
        val sintelUrl = "http://10.0.2.2:8080/media/sintel.mp4"
        val tearsOfSteelUrl = "http://10.0.2.2:8080/media/tears-of-steel.mp4"
        val elephantDreamUrl = "http://10.0.2.2:8080/media/elephants-dream.mp4"
        val forBiggerBlazesUrl = "http://10.0.2.2:8080/media/for-bigger-blazes.mp4"
        val forBiggerEscapesUrl = "http://10.0.2.2:8080/media/for-bigger-escapes.mp4"
        val forBiggerFunUrl = "http://10.0.2.2:8080/media/for-bigger-fun.mp4"
        val forBiggerJoyridesUrl = "http://10.0.2.2:8080/media/for-bigger-joyrides.mp4"

        // Squid Game - TV Show (replacing Stranger Things)
        val squidGameMain = Video(
            id = "squid-game",
            title = "Squid Game",
            type = "TV_SHOW",
            year = "2021",
            rating = "TV-MA",
            description = "Hundreds of cash-strapped players accept a strange invitation to compete in children's games. Inside, a tempting prize awaits with deadly high stakes.",
            posterUrl = "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg",
            videoUrl = bigBuckBunnyUrl
        )
        videos.add(squidGameMain)

        // Add Squid Game episodes
        episodes["squid-game"] = listOf(
            Episode(
                id = "sg-e1",
                title = "Red Light, Green Light",
                episodeNumber = 1,
                duration = "63m",
                description = "Hoping to win easy money, a broke and desperate Gi-hun agrees to take part in an enigmatic game. Not long into the first round, unforeseen horrors unfold.",
                videoUrl = bigBuckBunnyUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
            ),
            Episode(
                id = "sg-e2",
                title = "Hell",
                episodeNumber = 2,
                duration = "55m",
                description = "While the players form alliances, the second game's simple rules yield shocking results. A police detective receives an ominous phone call.",
                videoUrl = sintelUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
            ),
            Episode(
                id = "sg-e3",
                title = "The Man with the Umbrella",
                episodeNumber = 3,
                duration = "58m",
                description = "The third game's teams must work together to cross a bridge of glass panels. Gi-hun makes a bold move, and Sang-woo chooses self-preservation.",
                videoUrl = tearsOfSteelUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
            ),
            Episode(
                id = "sg-e4",
                title = "Stick to the Team",
                episodeNumber = 4,
                duration = "60m",
                description = "Players pair off for the fourth game. Gi-hun and his partner race to finish, but the round's true nature soon becomes clear.",
                videoUrl = elephantDreamUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
            ),
            Episode(
                id = "sg-e5",
                title = "A Fair World",
                episodeNumber = 5,
                duration = "57m",
                description = "The fifth game challenges players to cross a bridge, but not everyone will make it to the other side. The detective closes in on the truth.",
                videoUrl = forBiggerBlazesUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
            )
        )

        // The 100 - TV Show
        val the100 = Video(
            id = "the-100",
            title = "The 100",
            type = "TV_SHOW",
            year = "2014",
            rating = "TV-14",
            description = "Set 97 years after a nuclear war has destroyed civilization, when a spaceship housing humanity's lone survivors sends 100 juvenile delinquents back to Earth, hoping to repopulate the planet.",
            posterUrl = "https://image.tmdb.org/t/p/w500/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
            videoUrl = elephantDreamUrl
        )
        videos.add(the100)

        // Add The 100 episodes
        episodes["the-100"] = listOf(
            Episode(
                id = "100-s1-e1",
                title = "Pilot",
                episodeNumber = 1,
                duration = "42m",
                description = "Ninety-seven years ago, nuclear Armageddon decimated planet Earth, destroying civilization. The only survivors were the 400 inhabitants of 12 international space stations that were in orbit at the time.",
                videoUrl = elephantDreamUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg"
            ),
            Episode(
                id = "100-s1-e2",
                title = "Earth Skills",
                episodeNumber = 2,
                duration = "42m",
                description = "While Clarke and Wells search for Jasper, Bellamy leads a mission to find Mount Weather. Meanwhile, Octavia is determined to find a way out of the dropship.",
                videoUrl = forBiggerBlazesUrl,
                thumbnailUrl = "https://images.unsplash.com/photo-1462331940025-496dfbfc7564?w=500&h=750&fit=crop"
            )
        )

        // KPop Demon Hunters - Movie
        val kpopDemonHunters = Video(
            id = "kpop-demon-hunters",
            title = "KPop Demon Hunters",
            type = "MOVIE",
            year = "2025",
            rating = "PG",
            description = "Their fans may know them as pop stars, but the members of HUNTR/X are more than that: They're powerful warriors using their music to save the world.",
            posterUrl = "https://image.tmdb.org/t/p/w500/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
            videoUrl = forBiggerEscapesUrl,
            duration = "1h 39m"
        )
        videos.add(kpopDemonHunters)

        // The Avengers - Movie
        val theAvengers = Video(
            id = "the-avengers",
            title = "The Avengers",
            type = "MOVIE",
            year = "2012",
            rating = "PG-13",
            description = "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
            posterUrl = "https://image.tmdb.org/t/p/w500/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg",
            videoUrl = forBiggerFunUrl,
            duration = "2h 23m"
        )
        videos.add(theAvengers)

        // Wednesday - TV Show (replacing Dark)
        val wednesdayMain = Video(
            id = "wednesday-main",
            title = "Wednesday",
            type = "TV_SHOW",
            year = "2022",
            rating = "TV-14",
            description = "Wednesday Addams is sent to Nevermore Academy, a bizarre boarding school where she attempts to master her psychic powers, stop a killing spree of the town citizens, and solve the supernatural mystery that affected her family 25 years ago.",
            posterUrl = "https://image.tmdb.org/t/p/w500/jeGtaMwGxPmQN5xM4ClnwPQcNQz.jpg",
            videoUrl = sintelUrl
        )
        videos.add(wednesdayMain)

        // Add Wednesday episodes
        episodes["wednesday-main"] = listOf(
            Episode(
                id = "wed-e1",
                title = "Wednesday's Child is Full of Woe",
                episodeNumber = 1,
                duration = "50m",
                description = "After getting expelled, Wednesday Addams is sent to Nevermore Academy, where she attempts to master her psychic powers and solve a murder mystery.",
                videoUrl = forBiggerJoyridesUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/jeGtaMwGxPmQN5xM4ClnwPQcNQz.jpg"
            ),
            Episode(
                id = "wed-e2",
                title = "Woe Is the Loneliest Number",
                episodeNumber = 2,
                duration = "48m",
                description = "Wednesday tries to solve the murder mystery while navigating her new school. She discovers that the town has a dark history.",
                videoUrl = sintelUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/jeGtaMwGxPmQN5xM4ClnwPQcNQz.jpg"
            ),
            Episode(
                id = "wed-e3",
                title = "Friend or Woe",
                episodeNumber = 3,
                duration = "52m",
                description = "Wednesday investigates the monster attacks and uncovers more secrets about Nevermore Academy and her family's past.",
                videoUrl = tearsOfSteelUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/jeGtaMwGxPmQN5xM4ClnwPQcNQz.jpg"
            ),
            Episode(
                id = "wed-e4",
                title = "Woe What a Night",
                episodeNumber = 4,
                duration = "49m",
                description = "Wednesday attends the Rave'N dance and makes a shocking discovery about the monster and the person behind the attacks.",
                videoUrl = elephantDreamUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/jeGtaMwGxPmQN5xM4ClnwPQcNQz.jpg"
            ),
            Episode(
                id = "wed-e5",
                title = "You Reap What You Woe",
                episodeNumber = 5,
                duration = "54m",
                description = "Wednesday confronts the truth about the monster and the mastermind behind the attacks, leading to a dramatic confrontation.",
                videoUrl = forBiggerBlazesUrl,
                thumbnailUrl = "https://image.tmdb.org/t/p/w500/jeGtaMwGxPmQN5xM4ClnwPQcNQz.jpg"
            )
        )
    }
}