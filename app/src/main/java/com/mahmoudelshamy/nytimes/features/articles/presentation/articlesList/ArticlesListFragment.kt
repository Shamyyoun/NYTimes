package com.mahmoudelshamy.nytimes.features.articles.presentation.articlesList

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.mahmoudelshamy.nytimes.R
import com.mahmoudelshamy.nytimes.core.BaseFragment
import com.mahmoudelshamy.nytimes.databinding.FragmentArticlesListBinding
import com.mahmoudelshamy.nytimes.features.articles.domain.models.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesListFragment : BaseFragment<FragmentArticlesListBinding, ArticlesListViewModel>() {

    override fun onReady(savedInstanceState: Bundle?) {
        attachListeners()
        observeViewModel()
    }

    private fun attachListeners() = binding.run {
        widgetArticles.onItemClickListener = { item, _ ->
            viewModel.onEvent(ArticlesListEvent.ArticleClicked(item))
        }
    }

    private fun observeViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is ArticlesListViewState.Loading -> renderLoadingView()

                is ArticlesListViewState.Error -> renderErrorView(it.message)

                is ArticlesListViewState.Articles -> renderArticlesView(it.articles)

                is ArticlesListViewState.Empty -> renderEmptyView()
            }
        }
    }

    private fun renderArticlesView(articles: List<Article>) = binding.run {
        widgetArticles.submitList(articles)
        showView(widgetArticles, pbArticles, tvArticlesEmptyMsg, tvArticlesEmptyMsg)
    }

    private fun renderLoadingView() = binding.run {
        showView(pbArticles, tvArticlesErrorMsg, widgetArticles, tvArticlesEmptyMsg)
    }

    private fun renderErrorView(message: String? = null) = binding.run {
        tvArticlesErrorMsg.text = message ?: getString(R.string.something_went_wrong)
        showView(tvArticlesErrorMsg, widgetArticles, pbArticles, tvArticlesEmptyMsg)
    }

    private fun renderEmptyView() = binding.run {
        showView(tvArticlesEmptyMsg, tvArticlesErrorMsg, widgetArticles, pbArticles)
    }

    override val viewModel: ArticlesListViewModel by viewModels()

    override fun createViewBinding(): FragmentArticlesListBinding {
        return FragmentArticlesListBinding.inflate(LayoutInflater.from(requireContext()))
    }
}