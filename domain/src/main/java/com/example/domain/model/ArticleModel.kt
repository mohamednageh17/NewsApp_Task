package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel(
    val id: String? = null,
    val type: String? = null,
    val sectionID: String? = null,
    val sectionName: String? = null,
    val webPublicationDate: String? = null,
    val webTitle: String? = null,
    val webURL: String? = null,
    val apiURL: String? = null,
    val isHosted: Boolean? = null,
    val pillarID: String? = null,
    val pillarName: String? = null,
    var isFavourite: Boolean = false
) : Parcelable
