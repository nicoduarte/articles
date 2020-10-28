package com.nicoduarte.articles.ui.list

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nicoduarte.articles.R
import com.nicoduarte.articles.api.Result
import com.nicoduarte.articles.database.model.Article
import com.nicoduarte.articles.ui.base.BaseActivity
import com.nicoduarte.articles.ui.utils.showMessage
import com.nicoduarte.articles.ui.webview.WebViewActivity
import com.nicoduarte.articles.ui.webview.WebViewActivity.Companion.EXTRA_ARTICLE
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
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
        refreshLayout.setOnRefreshListener { viewModel.getArticles() }
    }

    private fun observerLiveData(results: Result<List<Article>>) {
        results.setState({
            refreshLayout.isRefreshing = false
            (rvArticles.adapter as ArticleAdapter).addArticles(it)
        }, {
            showMessage(rootView, it)
        }, {
            refreshLayout.isRefreshing = true
        })
    }

    private fun setupList() {
        rvArticles.adapter = ArticleAdapter(mutableListOf()) { goToDetail(it) }
        rvArticles.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        addSwipeDecoration()
    }

    private fun addSwipeDecoration() {
        val callback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = (rvArticles.adapter as ArticleAdapter).removeItem(position)
                Snackbar.make(viewHolder.itemView, getString(R.string.snackbar_message), Snackbar.LENGTH_LONG)
                        .setAction(android.R.string.cancel) {
                            (rvArticles.adapter as ArticleAdapter).addItem(item, position)
                            viewModel.removeId(item.id)
                }.show()
                viewModel.addPostId(item.id)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(this@ArticleListActivity, android.R.color.holo_red_dark))
                        .addActionIcon(R.drawable.ic_delete)
                        .create()
                        .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvArticles)
    }

    private fun goToDetail(article: Article) {
        startActivity(
                Intent(this, WebViewActivity::class.java)
                        .putExtra(EXTRA_ARTICLE, article)
        )
    }

}