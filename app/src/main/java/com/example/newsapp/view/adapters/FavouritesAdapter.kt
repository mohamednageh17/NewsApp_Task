package com.example.newsapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ArticleModel
import com.example.newsapp.databinding.FavouriteItemBinding

class FavouritesAdapter(private val onUnBookMark: OnUnBookMark) :
    RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {

    private var articles = ArrayList<ArticleModel?>()

    fun submitList(list: ArrayList<ArticleModel>) {
        articles.clear()
        articles.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article!!)
        holder.binding.unBookMarkFromFavourite.setOnClickListener {
            onUnBookMark.unBookMarkClickListener(it, article)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(
            FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = articles.size

    class FavouritesViewHolder(val binding: FavouriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticleModel) {
            with(binding) {
                articleTitleTextView.text = data.webTitle
                articleSectionNameTextView.text = data.sectionName
            }
        }
    }
}

interface OnUnBookMark {
    fun unBookMarkClickListener(view: View, articleModel: ArticleModel)
}