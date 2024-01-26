package com.aaguamaninga.myapplication.logic.usercases.jikan

import android.util.Log
import com.aaguamaninga.myapplication.data.network.endpoints.AnimeEndPoint
import com.aaguamaninga.myapplication.data.network.repository.RetrofitBase
import com.aaguamaninga.myapplication.logic.entities.FullInfoAnimeLG
import com.aaguamaninga.myapplication.core.Constants
import com.aaguamaninga.myapplication.core.getFullInfoAnimeLG

class JikanAnimeUserCase {
    suspend fun invoke(nameAnime: Int) : Result<FullInfoAnimeLG> {
        var result: Result<FullInfoAnimeLG>?= null
        var infoAnime = FullInfoAnimeLG()

        try {
            val baseService = RetrofitBase.getRetrofitJikanConnection()
            val service = baseService.create(AnimeEndPoint::class.java)
            val call = service.getAnimeFullInfo(nameAnime)


            if(call.isSuccessful){
                val a = call.body()!!
                infoAnime = a.getFullInfoAnimeLG()
                result = Result.success(infoAnime) // POSIBLE ERROR
            }else{
                Log.d(Constants.TAG, "Error en el llamado del API Jikan")
                result = Result.failure(Exception("Error en el llamado del API Jikan"))
            }
        }catch (ex:Exception){
            Log.e(Constants.TAG, ex.stackTraceToString())
        }

        return result!!

    }



}