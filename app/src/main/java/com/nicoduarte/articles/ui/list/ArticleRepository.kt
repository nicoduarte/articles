package com.nicoduarte.articles.ui.list

import android.app.Application
import com.nicoduarte.articles.api.ApiService
import com.nicoduarte.articles.database.ArticleDao
import com.nicoduarte.articles.database.ArticleDatabase
import com.nicoduarte.articles.database.model.Article
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleRepository(application: Application) {

    private val articleDao: ArticleDao

    init {
        val movieDatabase = ArticleDatabase.getDatabase(application)
        articleDao = movieDatabase.articleDao()
    }

    fun getArticles(page: Int): Observable<List<Article>> {
        return ApiService.getInstance()
            .getArticles()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.hits }
            .onErrorResumeNext { _: Throwable -> getArticlesFromDb() }
            .doOnNext {
                articleDao.insertAll(it)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
            }
    }

    private fun getArticlesFromDb(): Observable<List<Article>> {
        return articleDao.getArticles()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}