package com.example.newsapp.view.fragments

import android.content.Intent
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.model.ArticleModel
import com.example.movieapp.handle_state.StateData
import com.example.newsapp.view.activities.DetailsActivity
import com.example.newsapp.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    val articlesViewModel: ArticlesViewModel by viewModels()

    abstract fun initRecyclerView()
    abstract fun articlesObserver()

    private var progressBar: ProgressBar? = null

    fun setProgress(progressBar: ProgressBar) {
        this.progressBar = progressBar
    }

    fun showProgress() {
        progressBar!!.isVisible = true
    }

    fun hideProgress() {
        progressBar!!.isVisible = false
    }

    abstract fun handleSuccessState(it: StateData<List<ArticleModel>>)

    fun handleErrorState(it: Throwable?) {
        hideProgress()
        Toast.makeText(requireContext(), it!!.message, Toast.LENGTH_LONG).show()
    }

    fun launchDetailsActivity(articleModel: ArticleModel) {
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("article", articleModel)
        startActivity(intent)
    }

}