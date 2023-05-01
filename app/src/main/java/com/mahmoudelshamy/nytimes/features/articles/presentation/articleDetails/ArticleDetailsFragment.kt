package com.mahmoudelshamy.nytimes.features.articles.presentation.articleDetails

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.mahmoudelshamy.nytimes.features.common.presentation.base.BaseFragment
import com.mahmoudelshamy.nytimes.databinding.FragmentArticleDetailsBinding
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import com.mahmoudelshamy.nytimes.features.articles.presentation.articleDetails.ArticleDetailsViewState.ArticleDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment :
    BaseFragment<FragmentArticleDetailsBinding, ArticleDetailsViewModel>() {

    override fun onReady(savedInstanceState: Bundle?) {
        initViewModel()
        observeViewModel()
    }

    private fun initViewModel() {
        val args = ArticleDetailsFragmentArgs.fromBundle(requireArguments())
        viewModel.init(args.article)
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is ArticleDetails -> renderArticleView(it.article)
            }
        }
    }

    private fun renderArticleView(article: Article) = binding.run {
        widgetArticleDetails.render(article)
    }

    override val viewModel: ArticleDetailsViewModel by viewModels()

    override fun createViewBinding(): FragmentArticleDetailsBinding {
        return FragmentArticleDetailsBinding.inflate(LayoutInflater.from(requireContext()))
    }
}