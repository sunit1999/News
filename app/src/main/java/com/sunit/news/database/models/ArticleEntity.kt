package com.sunit.news.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "articles"
)
data class ArticleEntity(
    val id: UUID = UUID.randomUUID(),
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val sourceId: String?,
    val sourceName: String?,
    val title: String?,
    @PrimaryKey
    val url: String,
    val urlToImage: String?,
    val isBookmarked: Boolean,
)
