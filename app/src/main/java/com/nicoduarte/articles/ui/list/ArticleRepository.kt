package com.nicoduarte.articles.ui.list

import android.app.Application
import android.util.Log
import com.nicoduarte.articles.api.ApiService
import com.nicoduarte.articles.database.ArticleDao
import com.nicoduarte.articles.database.ArticleDatabase
import com.nicoduarte.articles.database.model.Article
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticleRepository(application: Application) {

    private val articleDao: ArticleDao

    init {
        val movieDatabase = ArticleDatabase.getDatabase(application)
        articleDao = movieDatabase.articleDao()
    }

    fun getArticles(): Observable<List<Article>> {
        return ApiService.getInstance()
            .getArticles()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.hits }
            .onErrorResumeNext { _: Throwable -> getArticlesFromDb() }
            .doOnNext { insertToDb(it) }
    }

    private fun insertToDb(list: List<Article>): Disposable {
        return articleDao.insertAll(list)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("Inserteds", list.size.toString())
            }, {
                Log.d("Error", it.message.toString())
            })
    }

    private fun getArticlesFromDb(): Observable<List<Article>> {
        return articleDao.getArticles()
            .toObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}