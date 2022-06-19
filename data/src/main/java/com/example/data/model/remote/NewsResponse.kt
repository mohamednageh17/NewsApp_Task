package com.example.data.model.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsResponse(
    @SerializedName("response")
    val response: Response? = null
)

@Keep
data class Response(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("userTier")
    val userTier: String? = null,
    @SerializedName("total")
    val total: Long? = null,
    @SerializedName("startIndex")
    val startIndex: Long? = null,
    @SerializedName("pageSize")
    val pageSize: Long? = null,
    @SerializedName("currentPage")
    val currentPage: Long? = null,
    @SerializedName("pages")
    val pages: Long? = null,
    @SerializedName("orderBy")
    val orderBy: String? = null,
    @SerializedName("results")
    val results: List<Articles>? = null,
)

@Keep
data class Articles(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("sectionId")
    val sectionID: String? = null,
    @SerializedName("sectionName")
    val sectionName: String? = null,
    @SerializedName("webPublicationDate")
    val webPublicationDate: String? = null,
    @SerializedName("webTitle")
    val webTitle: String? = null,
    @SerializedName("webUrl")
    val webURL: String? = null,
    @SerializedName("apiUrl")
    val apiURL: String? = null,
    @SerializedName("isHosted")
    val isHosted: Boolean? = null,
    @SerializedName("pillarId")
    val pillarID: String? = null,
    @SerializedName("pillarName")
    val pillarName: String? = null,
    var isFavourite: Boolean = false

)
