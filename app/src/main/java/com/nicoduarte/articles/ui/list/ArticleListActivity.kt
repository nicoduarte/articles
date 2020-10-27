package com.nicoduarte.articles.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.nicoduarte.articles.R
import com.nicoduarte.articles.api.Result
import com.nicoduarte.articles.database.model.Article
import com.nicoduarte.articles.ui.base.BaseActivity
import com.nicoduarte.articles.ui.utils.showMessage
import com.nicoduarte.articles.ui.webview.WebViewActivity
import com.nicoduarte.articles.ui.webview.WebViewActivity.Companion.EXTRA_ARTICLE
import kotlinx.android.synthetic.main.activity_main.*

class ArticleListActivity : BaseActivity() {

    private val viewModelFactory by lazy { ViewModelFactory(application) }
    private val viewModel by viewModels<ArticleViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(toolbar)
        setupList()
        viewModel.getArticleLiveData().observe(this, { observerLiveData(it) })
    }

    private fun observerLiveData(results: Result<List<Article>>) {
        results.setState({
            (rvArticles.adapter as ArticleAdapter).addArticles(it)
        }, {
            showMessage(rootView, it)
        }, {})
    }

    private fun setupList() {
        rvArticles.adapter = ArticleAdapter(mutableListOf()) { goToDetail(it) }
        rvArticles.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun goToDetail(article: Article) {
        startActivity(
            Intent(this, WebViewActivity::class.java)
                .putExtra(EXTRA_ARTICLE, article)
        )
    }

}