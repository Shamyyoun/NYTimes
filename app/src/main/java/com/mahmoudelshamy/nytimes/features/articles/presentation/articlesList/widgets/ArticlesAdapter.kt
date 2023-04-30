package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList.widgets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudelshamy.nytimes.databinding.ItemArticleBinding
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

class ArticlesAdapter : ListAdapter<Article, ArticlesAdapter.ViewHolder>(
    ArticleDiffCallback()
) {
    var onItemClickListener: ((Article, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    private fun createViewHolder(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // Set item click listener
            binding.widgetArticle.setOnClickListener {
                onItemClickListener?.invoke(
                    getItem(adapterPosition),
                    adapterPosition
                )
            }
        }

        fun bind(item: Article) = binding.run {
            widgetArticle.render(item)
        }
    }
}

private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article,
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article,
    ): Boolean {
        return oldItem == newItem
    }
}
