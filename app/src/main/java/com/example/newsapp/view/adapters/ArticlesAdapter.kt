package com.example.newsapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ArticleModel
import com.example.newsapp.databinding.ArticleItemBinding

class ArticlesAdapter(val onBookMark: OnBookMark) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    private var articles = ArrayList<ArticleModel?>()

    fun submitList(list: ArrayList<ArticleModel>) {
        articles.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(
            ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val article = articles[position]
        holder.itemView.setOnClickListener {
            onBookMark.viewArticleDetails(article!!)
        }
        holder.bind(article!!)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticlesViewHolder(val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticleModel) {
            binding.root.animation =
                AnimationUtils.loadAnimation(
                    binding.root.context,
                    com.google.android.material.R.anim.abc_fade_in
                )
            with(binding) {
                articleTitleTextView.text = data.webTitle
                articleSectionNameTextView.text = data.sectionName
            }
        }
    }
}

interface OnBookMark {
    fun viewArticleDetails(article: ArticleModel)
}