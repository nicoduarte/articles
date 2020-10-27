package com.nicoduarte.articles.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicoduarte.articles.database.model.Article

@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        private var INSTANCE: ArticleDatabase? = null
        private const val DATABASE_NAME = "article_database"

        fun getDatabase(context: Context): ArticleDatabase {
            if (INSTANCE == null) {
                synchronized(ArticleDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ArticleDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
