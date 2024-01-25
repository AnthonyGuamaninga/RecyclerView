package com.aaguamaninga.myapplication.data.network.entities.jikan.top

import com.guamaninga.myapplication.data.network.entities.jikan.top.Pagination

data class TopAnime(
    val `data`: List<Data> = listOf(),
    val pagination: Pagination?= null

)