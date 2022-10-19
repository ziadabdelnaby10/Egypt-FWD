package com.example.asteroidsproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidsproject.databinding.AsteroidListItemBinding
import com.example.asteroidsproject.model.Asteroid


class AsteroidRecyclerAdapter(val onClick: (Asteroid) -> Unit) :
    ListAdapter<Asteroid, AsteroidRecyclerAdapter.AsteroidViewHolder>(AsteroidDiffCallback()) {
    inner class AsteroidViewHolder(private val binding: AsteroidListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) onClick(getItem(adapterPosition))
            }
            val animation =
                AnimationUtils.loadAnimation(binding.root.context, android.R.anim.slide_in_left)
            animation.duration = 2000
            binding.root.startAnimation(animation)
        }

        fun bind(itemBinding: Asteroid) {
            binding.apply {
                asteroid = itemBinding
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AsteroidListItemBinding.inflate(layoutInflater, parent, false)
        return AsteroidViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AsteroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}
