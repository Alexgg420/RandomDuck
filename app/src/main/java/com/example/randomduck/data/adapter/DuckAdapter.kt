package com.example.randomduck.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.randomduck.data.model.Duck
import com.example.randomduck.databinding.DuckItemBinding

class DuckAdapter(private val onShowDetail: (d: Duck, view: View) -> Unit
) : ListAdapter<Duck, DuckAdapter.DuckListViewHolder>(PokemonDiffCallBack()) {


    inner class DuckListViewHolder(private val binding: DuckItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindDuck(d: Duck) {
            binding.duckImage.load(d.image)
            binding.card.setOnClickListener {
                onShowDetail(d, binding.root)
            }
        }

    }

    private class PokemonDiffCallBack : DiffUtil.ItemCallback<Duck>() {
        override fun areItemsTheSame(oldItem: Duck, newItem: Duck): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Duck, newItem: Duck): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuckListViewHolder {
        val binding = DuckItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DuckListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DuckListViewHolder, position: Int) {
        val duck = getItem(position)
        holder.bindDuck(duck)
    }
}