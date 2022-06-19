package com.example.newsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.domain.model.ArticleModel
import com.example.movieapp.handle_state.StateData
import com.example.newsapp.R
import com.example.newsapp.databinding.FavouriteArticlesFragmentBinding
import com.example.newsapp.view.adapters.FavouritesAdapter
import com.example.newsapp.view.adapters.OnUnBookMark


class FavouriteArticlesFragment : BaseFragment(), OnUnBookMark {

    private lateinit var binding: FavouriteArticlesFragmentBinding
    private val adapter by lazy { FavouritesAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavouriteArticlesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFavourites()

        setProgress(binding.progressBar)
        initRecyclerView()
        articlesObserver()
    }

    private fun loadFavourites() = articlesViewModel.getFavouriteArticlesFromLocalDB()

    override fun articlesObserver() {
        articlesViewModel.articlesLiveDate.observe(viewLifecycleOwner) {
            when (it!!.status) {
                StateData.DataStatus.LOADING -> showProgress()
                StateData.DataStatus.SUCCESS -> handleSuccessState(it)
                StateData.DataStatus.ERROR -> handleErrorState(it.error)
                else -> {
                }
            }
        }
    }

    override fun handleSuccessState(it: StateData<List<ArticleModel>>) {
        hideProgress()
        adapter.submitList(it.data as ArrayList<ArticleModel>)
    }

    override fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    override fun unBookMarkClickListener(view: View, articleModel: ArticleModel) {
        when (view.id) {
            R.id.unBookMarkFromFavourite -> unBookMarkArticle(articleModel)
        }
    }

    private fun unBookMarkArticle(articleModel: ArticleModel) {
        articlesViewModel.removeArticleFromFavouriteList(articleModel)
        Toast.makeText(
            activity,
            "the article is removed from your favourite list",
            Toast.LENGTH_SHORT
        ).show()
        loadFavourites()
    }
}