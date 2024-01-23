package com.aaguamaninga.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaguamaninga.myapplication.R
import com.aaguamaninga.myapplication.data.entities.Users
import com.aaguamaninga.myapplication.databinding.ItemsUsersBinding

class UsersAdapter() :RecyclerView.Adapter<UsersAdapter.UsersVH>() {
    var listUsers: List<Users> = listOf()
    class UsersVH(view: View): RecyclerView.ViewHolder(view){
        private var binding: ItemsUsersBinding = ItemsUsersBinding.bind(view)
        fun render(item: Users){
            binding.txtUserName.text = item.name
            binding.txtUserDesc.text = item.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        val inflater = LayoutInflater.from(parent.context)
        return UsersVH(inflater.inflate(R.layout.items_users, parent, false))
    }

    override fun getItemCount(): Int = listUsers.size

    override fun onBindViewHolder(holder: UsersVH, position: Int) {
        holder.render(listUsers[position])
    }
}
