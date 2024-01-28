package com.aaguamaninga.myapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.aaguamaninga.myapplication.R
import com.aaguamaninga.myapplication.databinding.FragmentDetailBinding
import com.aaguamaninga.myapplication.logic.entities.FullInfoAnimeLG
import com.aaguamaninga.myapplication.ui.viewmodels.DetailViewModel
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {

    private val detailVM : DetailViewModel by viewModels ()
    private lateinit var binding: FragmentDetailBinding
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
        initObservers()
        initListener()
        detailVM.loadInfoAnime(args.idAnime)
    }

    private fun initListener() {
        binding.btnRefresh.setOnClickListener{
            detailVM.loadInfoAnime(28977) // HARDCODE
        }
    }


    private fun initObservers(){
        detailVM.anime.observe(requireActivity()){anime ->
            binding.txtNameAnime.text = anime.name
            binding.sinopsis.text = anime.synapsis
            binding.imgAnime.load(anime.big_image)
            // demas binding
        }

        detailVM.error.observe(requireActivity()){errorMessage->
            Snackbar
                .make(requireActivity(), binding.btnRefresh, errorMessage.toString(), Snackbar.LENGTH_LONG)
                .show()

        }
    }


}
