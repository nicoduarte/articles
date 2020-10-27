package com.nicoduarte.articles.api

import com.nicoduarte.articles.api.response.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("search_by_date")
    fun getArticles(@Query("query") query: String = "android"): Single<ArticleResponse>

}