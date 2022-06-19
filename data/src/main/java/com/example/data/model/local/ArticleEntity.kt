package com.example.data.model.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "fav_articles")
@Parcelize
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "type")
    val type: String? = null,
    @ColumnInfo(name = "sectionID")
    val sectionID: String? = null,
    @ColumnInfo(name = "sectionName")
    val sectionName: String? = null,
    @ColumnInfo(name = "webPublicationDate")
    val webPublicationDate: String? = null,
    @ColumnInfo(name = "webTitle")
    val webTitle: String? = null,
    @ColumnInfo(name = "webURL")
    val webURL: String? = null,
    @ColumnInfo(name = "apiURL")
    val apiURL: String? = null,
    @ColumnInfo(name = "isHosted")
    val isHosted: Boolean? = null,
    @ColumnInfo(name = "pillarID")
    val pillarID: String? = null,
    @ColumnInfo(name = "pillarName")
    val pillarName: String? = null

) : Parcelable