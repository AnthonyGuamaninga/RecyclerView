package com.aaguamaninga.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aaguamaninga.myapplication.R
import com.aaguamaninga.myapplication.databinding.ItemsDetailBinding
import com.aaguamaninga.myapplication.logic.entities.FullInfoAnimeLG
import com.guamaninga.myapplication.data.network.entities.jikan.anime.Data

class DetailsAdapterDiffUtil : ListAdapter<FullInfoAnimeLG, DetailsAdapterDiffUtil.DetailVH>(DiffUtilDetailsCallback) {
    class DetailVH(view: View): RecyclerView.ViewHolder(view){
        private var binding: ItemsDetailBinding = ItemsDetailBinding.bind(view)
        fun render(item: FullInfoAnimeLG){
            binding.txtAnimeName.text = item.name
            binding.txtAnimeSynapsis.text = item.synapsis
            binding.imgAnime.load(item.big_image)

            binding.btnReturn.setOnClickListener {
                // implementar regreso
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailVH {
        val inflater = LayoutInflater.from(parent.context)
        return DetailVH(inflater.inflate(R.layout.items_detail, parent, false))
    }
    override fun onBindViewHolder(holder: DetailVH, position: Int) {
        holder.render(getItem(position))
    }
}


private object DiffUtilDetailsCallback : DiffUtil.ItemCallback<FullInfoAnimeLG>() {
    override fun areItemsTheSame(oldItem: FullInfoAnimeLG, newItem: FullInfoAnimeLG): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FullInfoAnimeLG, newItem: FullInfoAnimeLG): Boolean {
        return oldItem == newItem
    }

}