package com.aaguamaninga.myapplication.logic.usercases.jikan

import android.util.Log
import com.aaguamaninga.myapplication.data.network.endpoints.TopAnimesEndpoint
import com.aaguamaninga.myapplication.data.network.entities.jikan.top.TopAnime
import com.aaguamaninga.myapplication.data.network.repository.RetrofitBase
import com.aaguamaninga.myapplication.core.Constants
import com.aaguamaninga.myapplication.core.getFullInfoAnimeLG
import com.aaguamaninga.myapplication.logic.entities.FullInfoAnimeLG

class JikanGetTopAnimesUserCase {

    suspend fun invoke() : Result<List<FullInfoAnimeLG>> {
        var result: Result<List<FullInfoAnimeLG>>?= null
        val items = ArrayList<FullInfoAnimeLG>()

        try {
            val baseService = RetrofitBase.getRetrofitJikanConnection()
            val service = baseService.create(TopAnimesEndpoint::class.java)
            val call = service.getAllTopAnimes()

            if(call.isSuccessful){
                val infoAnime = call.body()!!
                infoAnime.data.forEach{
                    items.add(it.getFullInfoAnimeLG())
                }

                result = Result.success(items)
            }else{
                Log.e(Constants.TAG, "Error en el llamado del API Jikan")
                result = Result.failure(Exception("Error en el llamado del API Jikan"))
            }
        }catch (ex:Exception){
            Log.e(Constants.TAG, ex.stackTraceToString())
            result = Result.failure(Exception(ex))
        }

        return result!!
    }

}