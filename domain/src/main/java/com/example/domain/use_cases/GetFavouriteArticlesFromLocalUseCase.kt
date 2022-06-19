package com.example.domain.use_cases

import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class GetFavouriteArticlesFromLocalUseCase @Inject constructor(private val repository: NewsRepository) {

    operator fun invoke() = repository.getFavouriteArticlesFromLocalDB()
}