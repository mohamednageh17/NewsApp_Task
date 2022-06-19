package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.data.model.local.ArticleEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ArticleDao {

    @Insert(onConflict = REPLACE)
    fun insert(article: ArticleEntity): Completable

    @Delete
    fun delete(article: ArticleEntity): Completable

    @Query("delete from fav_articles")
    fun deleteAllFavouriteArticles()

    @Query("select * from fav_articles")
    fun getAllFavouriteArticles(): Single<List<ArticleEntity>>

    @Query("select * from fav_articles where id=:id")
    fun checkIfArticleIsFavourite(id:String):Single<ArticleEntity>
}