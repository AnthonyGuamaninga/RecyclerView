package com.aaguamaninga.myapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaguamaninga.myapplication.data.entities.Users
import com.aaguamaninga.myapplication.data.network.entities.jikan.top.TopAnime
import com.aaguamaninga.myapplication.databinding.ActivityMainBinding
import com.aaguamaninga.myapplication.logic.entities.FullInfoAnimeLG
import com.aaguamaninga.myapplication.logic.usercases.jikan.JikanGetTopAnimesUserCase
import com.aaguamaninga.myapplication.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}