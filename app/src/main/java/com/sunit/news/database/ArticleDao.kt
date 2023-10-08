package com.sunit.news.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunit.news.database.models.ArticleEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ArticleDao {

    @Query(
        """
        SELECT * from articles
        ORDER BY publishedAt DESC
    """
    )
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreArticles(entities: List<ArticleEntity>)

    @Query(
        """
        UPDATE articles
        SET isBookmarked = :isBookmarked
        WHERE id = :id
    """
    )
    suspend fun toggleBookmarkById(id: UUID, isBookmarked: Boolean)

    @Query(
        """
        SELECT * from articles
        WHERE isBookmarked = 1
        ORDER BY publishedAt DESC
    """
    )
    fun getAllBookmarkedArticles(): Flow<List<ArticleEntity>>
}
