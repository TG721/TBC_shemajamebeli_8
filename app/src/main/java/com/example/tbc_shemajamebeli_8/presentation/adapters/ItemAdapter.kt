package com.example.tbc_shemajamebeli_8.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_shemajamebeli_8.R
import com.example.tbc_shemajamebeli_8.data.model.Content

import com.example.tbc_shemajamebeli_8.databinding.ItemBinding
import com.example.tbc_shemajamebeli_8.domain.RecyclerViewInterface

class ItemAdapter(val recyclerViewInterface: RecyclerViewInterface) : ListAdapter<Content, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    inner class ItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val buyButton = binding.buyButton
        private val favoriteButton = binding.favoriteButton

        fun bind() {
            //we are not making this button functional but could change in future
            favoriteButton.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    recyclerViewInterface.onFavoriteButtonClick(pos)
                }
            }
                buyButton.setOnClickListener {
                    val pos = bindingAdapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onBuyButtonClick(pos)
                    }
                }

                val source = getItem(absoluteAdapterPosition)
                binding.itemCost.text  = source.price
                binding.itemName.text = source.title
                Glide.with(this.binding.imagePerson)
                    .load(source.cover)
                    .into(binding.imagePerson)
            if(source.liked) favoriteButton.setImageResource(R.drawable.ic_heart_active_button)
            else favoriteButton.setImageResource(R.drawable.ic_heart_not_active_button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }


}

private class ItemDiffCallback : DiffUtil.ItemCallback<Content>() {
    override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean =
        oldItem == newItem

}

