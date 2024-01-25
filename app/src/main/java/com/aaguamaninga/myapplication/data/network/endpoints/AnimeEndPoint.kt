package com.aaguamaninga.myapplication.data.network.endpoints


import com.aaguamaninga.myapplication.data.network.entities.jikan.anime.FullInfoAnime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeEndPoint {
    @GET("anime/{id}/full")
    fun getAnimeFullInfo(@Path("id") name:Int): Response<FullInfoAnime>


}