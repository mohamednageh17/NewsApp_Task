package com.example.domain.repository

import com.example.domain.model.ArticleModel
import io.reactivex.Completable
import io.reactivex.Single

interface NewsRepository {

    fun fetchNewsFeedFromApi(page: Int): Single<List<ArticleModel>>

    fun addArticleToFavourite(article: ArticleModel): Completable

    fun removeArticleFromFavourite(article: ArticleModel): Completable

    fun getFavouriteArticlesFromLocalDB(): Single<List<ArticleModel>>

    fun checkIfArticleIsFavourite(id: String):Single<ArticleModel>
}