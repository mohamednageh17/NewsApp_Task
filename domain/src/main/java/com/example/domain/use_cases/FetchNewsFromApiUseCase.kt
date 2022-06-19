package com.example.domain.use_cases

import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class FetchNewsFromApiUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(page: Int) = repository.fetchNewsFeedFromApi(page)
}