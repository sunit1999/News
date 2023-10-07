package com.sunit.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sunit.news.database.models.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class
    ],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
