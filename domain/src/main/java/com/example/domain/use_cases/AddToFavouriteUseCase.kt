package com.example.domain.use_cases

import com.example.domain.model.ArticleModel
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(article: ArticleModel) = repository.addArticleToFavourite(article)
}