package com.nicoduarte.articles.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicoduarte.articles.api.Result
import com.nicoduarte.articles.database.model.Article
import io.reactivex.disposables.CompositeDisposable

class ArticleViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val articleLiveData = MutableLiveData<Result<List<Article>>>()
    private val postIds = mutableListOf<String>()

    init { getArticles() }

    fun getArticles() {
        articleLiveData.postValue(Result.loading())
        val disposable = articleRepository.getArticles()
            .subscribe(this::onSuccessMovies, this::onErrorMovies)
        compositeDisposable.add(disposable)
    }

    private fun onErrorMovies(error: Throwable) {
        articleLiveData.postValue(Result.error(message = error.message))
    }

    private fun onSuccessMovies(list: List<Article>) {
        val filteredList = list.filter { !postIds.contains(it.id) }
        articleLiveData.postValue(Result.success(filteredList))
    }

    fun getArticleLiveData(): LiveData<Result<List<Article>>> = articleLiveData

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun addPostId(id: String) {
        postIds.add(id)
    }

    fun removeId(id: String) {
        postIds.remove(id)
    }
}