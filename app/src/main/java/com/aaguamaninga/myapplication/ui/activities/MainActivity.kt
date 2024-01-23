package com.aaguamaninga.myapplication.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaguamaninga.myapplication.R
import com.aaguamaninga.myapplication.data.entities.Users
import com.aaguamaninga.myapplication.databinding.ActivityMainBinding
import com.aaguamaninga.myapplication.ui.adapters.UsersAdapter
import com.aaguamaninga.myapplication.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var usersList : MutableList<Users> = ArrayList<Users>()
    private var count : Int = 1

    private lateinit var binding: ActivityMainBinding
    private var usersAdapter = UsersAdapter({deleteUsers(it)}, {selectUser(it)})

    private var userDiffAdapter = UsersAdapterDiffUtil({deleteUsersDiff(it)}, {selectUser(it)})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView(){
        binding.rvUsers.adapter = userDiffAdapter
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
                "https://img.freepik.com/vector-premium/ilustracion-avatar-estudiante-icono-perfil-usuario-avatar-jovenes_118339-4395.jpg"
            )
            count++
            insertUsersDiff(us)

        }
    }
    private fun insertUsersDiff(us: Users){
        usersList.add(us)
        userDiffAdapter.submitList(usersList.toList())

    }
    private fun deleteUsersDiff(position: Int){
        usersList.removeAt(position)
        userDiffAdapter.submitList(usersList.toList())

    }
    private fun insertUsers(us: Users){
        usersList.add(us)
        usersAdapter.listUsers = usersList
        usersAdapter.notifyItemInserted(usersList.size)
    }
    private fun deleteUsers(position: Int){
        usersList.removeAt(position)
        usersAdapter.listUsers = usersList
        usersAdapter.notifyItemRemoved(position)
    }


    private fun selectUser(user: Users){
        Snackbar
            .make(this, binding.btnInsert, user.name, Snackbar.LENGTH_LONG)
            .show()
        /*val i  = Intent(this, layoutdellegada)
        i.putExtra("usuarioID", user.id)
        startActivity(i)*/
    }
}