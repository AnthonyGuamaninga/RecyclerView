package com.aaguamaninga.myapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaguamaninga.myapplication.R
import com.aaguamaninga.myapplication.databinding.FragmentListBinding
import com.aaguamaninga.myapplication.logic.entities.FullInfoAnimeLG
import com.aaguamaninga.myapplication.logic.usercases.jikan.JikanGetTopAnimesUserCase
import com.aaguamaninga.myapplication.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    private var usersList : MutableList<FullInfoAnimeLG> = ArrayList()
    private var userDiffAdapter = UsersAdapterDiffUtil({deleteUsersDiff(it)}, {selectAnime(it)})
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentListBinding.bind(inflater.inflate(R.layout.fragment_list, container, false))
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.rvUsers.adapter = userDiffAdapter
        binding.rvUsers.layoutManager=
            LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.VERTICAL,
                false
            )
        loadDataRecyclerView()

    }

    private fun loadDataRecyclerView(){
        lifecycleScope.launch (Dispatchers.Main) {
            binding.progresBar.visibility = View.VISIBLE

            val resp = withContext(Dispatchers.IO){
                JikanGetTopAnimesUserCase().invoke()
            }
            resp.onSuccess {listAnime ->
                usersList.addAll(listAnime)
                insertUsersDiff(usersList)
            }
            resp.onFailure {ex ->
                Snackbar.make(
                    requireActivity(),
                    binding.rvUsers,
                    ex.message.toString(),
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }

            binding.progresBar.visibility = View.GONE
        }
    }

    private fun insertUsersDiff(animes: List<FullInfoAnimeLG>){
        usersList.addAll(animes)
        userDiffAdapter.submitList(usersList.toList())

    }
    private fun deleteUsersDiff(position: Int){
        usersList.removeAt(position)
        userDiffAdapter.submitList(usersList.toList())

    }



    private fun selectAnime(anime: FullInfoAnimeLG){
        findNavController()
            .navigate(
                ListFragmentDirections
                    .actionListFragmentToDetailFragment(idAnime = anime.id)
            )
    }

}




