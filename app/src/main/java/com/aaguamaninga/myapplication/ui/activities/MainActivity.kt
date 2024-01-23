package com.aaguamaninga.myapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaguamaninga.myapplication.R
import com.aaguamaninga.myapplication.data.entities.Users
import com.aaguamaninga.myapplication.databinding.ActivityMainBinding
import com.aaguamaninga.myapplication.ui.adapters.UsersAdapter

class MainActivity : AppCompatActivity() {
    private var usersList : MutableList<Users> = ArrayList<Users>()
    private var count : Int = 1

    private lateinit var binding: ActivityMainBinding
    private var usersAdapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView(){
        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.layoutManager=
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )

    }

    private fun initListeners(){
        binding.btnInsert.setOnClickListener {

            val us = Users(
                1,"Alfred $count" ,"Estudiante",
                "https://e7.pngegg.com/pngimages/799/987/png-clipart-computer-icons-avatar-icon-design-avatar-heroes-computer-wallpaper.png"
            )
            count++
            usersList.add(us)

            usersAdapter.listUsers = usersList
            usersAdapter.notifyDataSetChanged()

        }
    }
}