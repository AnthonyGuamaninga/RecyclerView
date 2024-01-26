package com.aaguamaninga.myapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaguamaninga.myapplication.R
import com.aaguamaninga.myapplication.databinding.FragmentDetailBinding
import com.aaguamaninga.myapplication.logic.entities.FullInfoAnimeLG
import com.aaguamaninga.myapplication.logic.usercases.jikan.JikanAnimeUserCase
import com.aaguamaninga.myapplication.logic.usercases.jikan.JikanGetTopAnimesUserCase
import com.aaguamaninga.myapplication.ui.adapters.DetailsAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment : Fragment() {
   private lateinit var binding :FragmentDetailBinding

    private var animeDetail : MutableList<FullInfoAnimeLG> = ArrayList()
    private val detailDiffAdapter = DetailsAdapterDiffUtil()

   val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding =
             FragmentDetailBinding.bind(inflater.inflate(R.layout.fragment_detail, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.txtIdAnime.text = args.idAnime.toString()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.rvDetails.adapter = detailDiffAdapter
        binding.rvDetails.layoutManager=
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
                JikanAnimeUserCase().invoke(args.idAnime) // POSIBLE ERROR
            }
            resp.onSuccess {
                animeDetail.add(it)
                detailDiffAdapter.submitList(animeDetail.toList())
            }
            resp.onFailure {ex ->
                Snackbar.make(
                    requireActivity(),
                    binding.rvDetails,
                    ex.message.toString(),
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }

            binding.progresBar.visibility = View.GONE
        }
    }




}