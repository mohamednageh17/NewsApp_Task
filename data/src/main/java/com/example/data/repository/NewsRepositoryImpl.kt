package com.example.data.repository

import com.example.data.local.ArticlesDatabase
import com.example.data.mapper.mapToDomain
import com.example.data.mapper.mapToEntity
import com.example.data.remote.NewsApi
import com.example.domain.model.ArticleModel
import com.example.domain.repository.NewsRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val localDB: ArticlesDatabase
) : NewsRepository {

    override fun fetchNewsFeedFromApi(page: Int): Single<List<ArticleModel>> {
        return api.fetchNewsFeed(page).map {
            it.mapToDomain()
        }
    }

    override fun addArticleToFavourite(article: ArticleModel): Completable {
        return localDB.articleDao().insert(article = article.mapToEntity())
    }

    override fun removeArticleFromFavourite(article: ArticleModel): Completable {
        return localDB.articleDao().delete(article.mapToEntity())
    }

    override fun getFavouriteArticlesFromLocalDB(): Single<List<ArticleModel>> {
        return localDB.articleDao().getAllFavouriteArticles().map {
            it.mapToDomain()
        }
    }

    override fun checkIfArticleIsFavourite(id: String): Single<ArticleModel> {
        return localDB.articleDao().checkIfArticleIsFavourite(id).map {
            it.mapToDomain()
        }
    }
}