package com.example.movieapp.di

import com.example.data.local.ArticlesDatabase
import com.example.data.remote.NewsApi
import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun getRepositoryModule(api: NewsApi, localDb: ArticlesDatabase): NewsRepository {
        return NewsRepositoryImpl(api, localDb)
    }

}
