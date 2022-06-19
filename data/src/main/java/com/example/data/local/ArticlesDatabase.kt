package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.local.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}