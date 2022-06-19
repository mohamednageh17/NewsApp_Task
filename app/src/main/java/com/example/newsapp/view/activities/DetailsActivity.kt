package com.example.newsapp.view.activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.ArticleModel
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityDetailsBinding
import com.example.newsapp.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    val articlesViewModel: ArticlesViewModel by viewModels()
    lateinit var articleModel: ArticleModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articleModel = (intent.getParcelableExtra("article") as? ArticleModel)!!

        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        articlesViewModel.checkIfArticleIsFavourite(articleModel.id!!)
        articlesObserver()
    }

    private fun initViews() {
        binding.articleSectionNameTextView.text = articleModel.sectionName
        binding.articleWebTitleTextView.text = articleModel.webTitle
        binding.articleUrlTextView.text = articleModel.webURL
        setupLink(binding.articleUrlTextView, articleModel.webURL)
        onBookMarkClicked(articleModel)
        onShareIconClicked(articleModel)
    }

    private fun setupLink(textView: TextView, url: String?) {
        textView.text = "Read the whole article in details"
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.setLinkTextColor(Color.BLUE)
        textView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }

    fun articlesObserver() {
        articlesViewModel.isFavourite.observe(this) {
            when (it) {
                true -> isFavouriteState()
                false -> isUnFavouriteState()
            }
        }
    }

    private fun isFavouriteState() {
        articleModel.isFavourite = true
        binding.bookMark.setImageResource(R.drawable.bookmark_icon_gray)
    }

    private fun isUnFavouriteState() {
        articleModel.isFavourite = false
        binding.bookMark.setImageResource(R.drawable.bookmark_icon_white)
    }

    private fun onBookMarkClicked(articleModel: ArticleModel) {
        binding.bookMark.setOnClickListener {
            when (articleModel.isFavourite) {
                true -> unBookMark(articleModel)
                false -> bookMark(articleModel)
            }
        }
    }

    private fun unBookMark(articleModel: ArticleModel) {
        articleModel.isFavourite = false
        articlesViewModel.removeArticleFromFavouriteList(articleModel)
        Toast.makeText(
            this,
            "the article is removed from your favourite list",
            Toast.LENGTH_SHORT
        ).show()
        initViewModel()
    }


    private fun bookMark(articleModel: ArticleModel) {
        articleModel.isFavourite = true
        articlesViewModel.addArticleToFavouriteList(articleModel)
        Toast.makeText(
            this,
            "Congratulations \n the article is saved to your favourite list..",
            Toast.LENGTH_SHORT
        ).show()
        initViewModel()
    }

    private fun onShareIconClicked(articleModel: ArticleModel) {
        binding.share.setOnClickListener {
            shareArticle(articleModel)
        }
    }

    private fun shareArticle(articleModel: ArticleModel) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "${articleModel.webTitle} \n ${articleModel.webURL}")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}