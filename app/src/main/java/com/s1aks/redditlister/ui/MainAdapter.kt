package com.s1aks.redditlister.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.s1aks.redditlister.data.RedditPost
import com.s1aks.redditlister.databinding.ListItemBinding

class MainAdapter : PagingDataAdapter<RedditPost, MainAdapter.MainHolder>(DataDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(position)
    }

    inner class MainHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = getItem(position) ?: return
            binding.title.text = data.title
            binding.upvotes.text = data.score.toString()
            binding.messages.text = data.num_comments.toString()
        }

    }

    object DataDiffCallback : DiffUtil.ItemCallback<RedditPost>() {
        override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost) =
            oldItem == newItem
    }
}