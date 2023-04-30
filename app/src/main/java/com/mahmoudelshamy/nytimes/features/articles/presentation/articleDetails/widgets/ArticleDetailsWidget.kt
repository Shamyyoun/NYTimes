package com.mahmoudelshamy.nytimes.features.articles.presentation.articleDetails.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import coil.load
import com.mahmoudelshamy.nytimes.R
import com.mahmoudelshamy.nytimes.common.extensions.inflater
import com.mahmoudelshamy.nytimes.databinding.WidgetArticleDetailsBinding
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article

class ArticleDetailsWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = WidgetArticleDetailsBinding.inflate(
        context.inflater,
        this,
        true
    )

    fun render(article: Article) = binding.run {
        tvArticleTitle.text = article.title
        tvArticleDate.text = article.publishedAt
        tvArticleContent.text = article.content

        val authors = article.persons.joinToString(", ")
        tvArticleAuthors.text = root.context.getString(
            R.string.by_s,
            authors
        )
        tvArticleAuthors.isVisible = authors.isNotEmpty()

        ivArticleImage.load(article.imageUrl) {
            placeholder(R.drawable.default_image)
            error(R.drawable.default_image)
        }
    }
}
