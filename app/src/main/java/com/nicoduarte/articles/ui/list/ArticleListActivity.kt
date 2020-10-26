package com.nicoduarte.articles.ui.list

import android.os.Bundle
import com.nicoduarte.articles.R
import com.nicoduarte.articles.database.Article
import com.nicoduarte.articles.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class ArticleListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(toolbar)
        setupList()
    }

    private fun setupList() {
        rvArticles.adapter = ArticleAdapter(mutableListOf()) { goToDetail(it) }
    }

    private fun goToDetail(article: Article) {

    }

}