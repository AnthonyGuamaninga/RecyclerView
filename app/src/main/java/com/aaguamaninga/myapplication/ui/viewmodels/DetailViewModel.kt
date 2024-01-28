package com.aaguamaninga.myapplication.ui.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.aaguamaninga.myapplication.logic.entities.FullInfoAnimeLG
import com.aaguamaninga.myapplication.logic.usercases.jikan.JikanAnimeUserCase
import com.aaguamaninga.myapplication.ui.fragment.DetailFragmentDirections
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel: ViewModel() {

    val anime = MutableLiveData<FullInfoAnimeLG>()
    val error = MutableLiveData<String>()

    fun loadInfoAnime(animeID: Int){
        viewModelScope.launch (Dispatchers.Main) {

            val data = withContext(Dispatchers.IO){
                JikanAnimeUserCase().invoke(animeID)
            }
            data.onSuccess {
                it.name = it.name +" UCE"
                anime.postValue(it)
            }
            data.onFailure {

            }

        }
    }





}