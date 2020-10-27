package com.nicoduarte.articles.ui.list

import android.app.Application
import com.nicoduarte.articles.api.ApiService
import com.nicoduarte.articles.api.response.ArticleResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleRepository(application: Application) {

    fun getArticles(page: Int): Single<ArticleResponse> {
        return ApiService.getInstance()
            .getArticles()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}