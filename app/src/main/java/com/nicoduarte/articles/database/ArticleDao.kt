package com.nicoduarte.articles.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicoduarte.articles.database.model.Article
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ArticleDao {

    @Query("SELECT * from article_table")
    fun getArticles(): Single<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>): Completable
}
