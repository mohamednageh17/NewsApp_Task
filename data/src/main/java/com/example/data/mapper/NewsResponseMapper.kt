package com.example.data.mapper

import com.example.data.model.local.ArticleEntity
import com.example.data.model.remote.NewsResponse
import com.example.domain.model.ArticleModel

fun NewsResponse.mapToDomain() = this.response!!.results!!.map {
    ArticleModel(
        id = it.id,
        type = it.type,
        sectionID = it.sectionID,
        sectionName = it.sectionName,
        webPublicationDate = it.webPublicationDate,
        webTitle = it.webTitle,
        webURL = it.webURL,
        apiURL = it.apiURL,
        isHosted = it.isHosted,
        pillarID = it.pillarID,
        pillarName = it.pillarName,
        isFavourite = it.isFavourite
    )
}

fun ArticleModel.mapToEntity(): ArticleEntity {
    return ArticleEntity(
        id = this.id!!,
        type = this.type,
        sectionID = this.sectionID,
        sectionName = this.sectionName,
        webPublicationDate = this.webPublicationDate,
        webTitle = this.webTitle,
        webURL = this.webURL,
        apiURL = this.apiURL,
        isHosted = this.isHosted,
        pillarID = this.pillarID,
        pillarName = this.pillarName
    )
}

fun List<ArticleEntity>.mapToDomain(): List<ArticleModel> {
    return this.map {
        ArticleModel(
            id = it.id,
            type = it.type,
            sectionID = it.sectionID,
            sectionName = it.sectionName,
            webPublicationDate = it.webPublicationDate,
            webTitle = it.webTitle,
            webURL = it.webURL,
            apiURL = it.apiURL,
            isHosted = it.isHosted,
            pillarID = it.pillarID,
            pillarName = it.pillarName
        )
    }
}

fun ArticleEntity.mapToDomain(): ArticleModel {
    return ArticleModel(
        id = this.id,
        type = this.type,
        sectionID = this.sectionID,
        sectionName = this.sectionName,
        webPublicationDate = this.webPublicationDate,
        webTitle = this.webTitle,
        webURL = this.webURL,
        apiURL = this.apiURL,
        isHosted = this.isHosted,
        pillarID = this.pillarID,
        pillarName = this.pillarName
    )
}
