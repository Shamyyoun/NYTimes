package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import coil.load
import com.mahmoudelshamy.nytimes.R
import com.mahmoudelshamy.nytimes.utils.extensions.inflater
import com.mahmoudelshamy.nytimes.databinding.WidgetArticleBinding
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

class ArticleItemWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = WidgetArticleBinding.inflate(
        context.inflater,
        this,
        true
    )

    fun render(article: Article) = binding.run {
        tvItemTitle.text = article.title
        tvItemDate.text = article.publishedAt

        val authors = article.persons.joinToString(", ")
        tvItemAuthors.text = root.context.getString(
            R.string.by_s,
            authors
        )
        tvItemAuthors.isVisible = authors.isNotEmpty()

        ivItemImage.load(article.thumbnailUrl) {
            placeholder(R.drawable.default_image)
            error(R.drawable.default_image)
        }
    }
}
