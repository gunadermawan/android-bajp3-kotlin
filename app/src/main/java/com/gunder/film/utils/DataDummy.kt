package com.gunder.film.utils

import com.gunder.film.data.source.local.entity.DetailEntity
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.data.source.remote.response.DetailResponse
import com.gunder.film.data.source.remote.response.ListResponse

object DataDummy {
    fun generateDummyMovies(): ArrayList<ListEntity> {
        val movies = ArrayList<ListEntity>()

        movies.add(
            ListEntity(
                460465,
                "Mortal Kombat",
                null,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2018"
            )
        )
        movies.add(
            ListEntity(
                399566,
                "Godzilla vs. Kong",
                null,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2018"
            )
        )

        return movies
    }

    fun generateDummyTvShow(): ArrayList<ListEntity> {
        val tvShow = ArrayList<ListEntity>()

        tvShow.add(
            ListEntity(
                88396,
                "Squid Game",
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "Hundreds of cash-strapped players accept a strange invitation to compete in children's games—with high stakes. But, a tempting prize awaits the victor.",
                null
            )
        )
        tvShow.add(
            ListEntity(
                85720,
                null,
                "Shadow and Bone",
                "/mrVoyDFiDSqfH4mkoRtccOv2vwh.jpg",
                "/8z9qQkx7wA6FXpLV8Tiw9mfsRFK.jpg",
                "In a world cleaved in two by a massive barrier of perpetual darkness, a young soldier uncovers a power that might finally unite her country. But as she struggles to hone her power, dangerous forces plot against her. Thugs, thieves, assassins and saints are at war now, and it will take more than magic to survive.",
                null
            )
        )

        return tvShow
    }

    fun generateRemoteDummyMovies(): ArrayList<ListResponse> {
        val movies = ArrayList<ListResponse>()

        movies.add(
            ListResponse(
                460465,
                "Mortal Kombat",
                null,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2018"
            )
        )
        movies.add(
            ListResponse(
                399566,
                "Godzilla vs. Kong",
                null,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2018"
            )
        )

        return movies
    }

    fun generateRemoteDummyTvShow(): ArrayList<ListResponse> {
        val tvShow = ArrayList<ListResponse>()

        tvShow.add(
            ListResponse(
                88396,
                null,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2018"
            )
        )
        tvShow.add(
            ListResponse(
                85720,
                null,
                "Shadow and Bone",
                "/mrVoyDFiDSqfH4mkoRtccOv2vwh.jpg",
                "/8z9qQkx7wA6FXpLV8Tiw9mfsRFK.jpg",
                "In a world cleaved in two by a massive barrier of perpetual darkness, a young soldier uncovers a power that might finally unite her country. But as she struggles to hone her power, dangerous forces plot against her. Thugs, thieves, assassins and saints are at war now, and it will take more than magic to survive.",
                "2018"
            )
        )

        return tvShow
    }

    fun generateDataDummyMovies(): ArrayList<DetailEntity> {
        val movies = ArrayList<DetailEntity>()

        movies.add(
            DetailEntity(
                460465,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Mortal Kombat",
                "Dune",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
                "2021-09-15"
            )
        )
        movies.add(
            DetailEntity(
                399566,
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Godzilla vs. Kong",
                null,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
                "2021-09-15"
            )
        )

        return movies
    }

    fun generateDataDummyTvShow(): ArrayList<DetailEntity> {
        val tvShow = ArrayList<DetailEntity>()

        tvShow.add(
            DetailEntity(
                88396,
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                null,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2018"
            )
        )
        tvShow.add(
            DetailEntity(
                85720,
                "/8z9qQkx7wA6FXpLV8Tiw9mfsRFK.jpg",
                null,
                "Shadow and Bone",
                "In a world cleaved in two by a massive barrier of perpetual darkness, a young soldier uncovers a power that might finally unite her country. But as she struggles to hone her power, dangerous forces plot against her. Thugs, thieves, assassins and saints are at war now, and it will take more than magic to survive.",
                "2018"
            )
        )

        return tvShow
    }

    fun generateRemoteDataDummyMovies(): ArrayList<DetailResponse> {
        val movies = ArrayList<DetailResponse>()

        movies.add(
            DetailResponse(
                460465,
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "Mortal Kombat",
                null,
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2018"
            )
        )
        movies.add(
            DetailResponse(
                399566,
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "Godzilla vs. Kong",
                null,
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "2018"
            )
        )

        return movies
    }

    fun generateRemoteDataDummyTvShow(): ArrayList<DetailResponse> {
        val tvShow = ArrayList<DetailResponse>()

        tvShow.add(
            DetailResponse(
                88396,
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                null,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2018"
            )
        )
        tvShow.add(
            DetailResponse(
                85720,
                "/8z9qQkx7wA6FXpLV8Tiw9mfsRFK.jpg",
                null,
                "Shadow and Bone",
                "In a world cleaved in two by a massive barrier of perpetual darkness, a young soldier uncovers a power that might finally unite her country. But as she struggles to hone her power, dangerous forces plot against her. Thugs, thieves, assassins and saints are at war now, and it will take more than magic to survive.",
                "2018"
            )
        )

        return tvShow
    }
}