package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudelshamy.nytimes.utils.extensions.inflater
import com.mahmoudelshamy.nytimes.databinding.WidgetArticlesListBinding
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

class ArticlesListWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = WidgetArticlesListBinding.inflate(
        context.inflater,
        this,
        true
    )

    private val articlesAdapter by lazy {
        ArticlesAdapter()
    }

    var onItemClickListener: ((Article, Int) -> Unit)? = null

    init {
        setupRecycler()
    }

    private fun setupRecycler() = binding.rvArticles.apply {
        adapter = articlesAdapter

        addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )

        articlesAdapter.onItemClickListener = { item, position ->
            onItemClickListener?.invoke(item, position)
        }
    }

    fun submitList(data: List<Article>) {
        articlesAdapter.submitList(data)
    }
}
