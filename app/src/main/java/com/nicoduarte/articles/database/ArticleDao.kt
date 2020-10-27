package com.nicoduarte.articles.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicoduarte.articles.database.model.Article
import io.reactivex.Observable

@Dao
interface ArticleDao {

    @Query("SELECT * from article_table")
    fun getArticles(): Observable<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Article>)
}