package com.example.newsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ArticleModel
import com.example.movieapp.handle_state.StateData
import com.example.newsapp.Utils.CheckInternetConnection
import com.example.newsapp.databinding.NewsFeedFragmentBinding
import com.example.newsapp.view.adapters.ArticlesAdapter
import com.example.newsapp.view.adapters.OnBookMark

class NewsFeedFragment : BaseFragment(), OnBookMark {

    private lateinit var binding: NewsFeedFragmentBinding
    private val adapter by lazy { ArticlesAdapter(this) }
    private lateinit var mLayoutManager: LinearLayoutManager
    private var loading = true
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsFeedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        CheckInternetConnection(this.requireContext()).observe(viewLifecycleOwner) {
            when (it) {
                true -> onInternetConnection()
                false -> onNoInternetConnection()
            }
        }
    }

    private fun onInternetConnection() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.noInternetConnectionCardView.visibility = View.GONE

        articlesViewModel.fetchNewsFeed(page)
        setProgress(binding.progressBar)
        initRecyclerView()
        articlesObserver()
    }

    private fun onNoInternetConnection() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.noInternetConnectionCardView.visibility = View.VISIBLE
    }

    override fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        setRVLayoutManager()
        setRVScrollListener()
    }

    private fun setRVLayoutManager() {
        mLayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun setRVScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = mLayoutManager.childCount
                val totalItemCount = mLayoutManager.itemCount
                val firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()

                if (loading) {
                    if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                        loading = false
                        page++
                        showProgress()
                        articlesViewModel.fetchNewsFeed(page)
                        loading = true
                    }
                }
            }
        })
    }

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

    override fun viewArticleDetails(article: ArticleModel) {
        launchDetailsActivity(article)
    }
}