package com.nicoduarte.articles.ui.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(application: Application)
    : ViewModelProvider.NewInstanceFactory() {

    private val repository = ArticleRepository(application)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            ArticleViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}