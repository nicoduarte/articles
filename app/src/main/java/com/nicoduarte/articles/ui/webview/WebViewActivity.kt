package com.nicoduarte.articles.ui.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.nicoduarte.articles.R
import com.nicoduarte.articles.database.Article
import com.nicoduarte.articles.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    companion object {
        const val EXTRA_ARTICLE = "EXTRA_ARTICLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        toolbarToLoad(toolbar)
        enableHomeDisplay(true)

        val article = intent.getParcelableExtra<Article>(EXTRA_ARTICLE)
        article?.storyUrl?.let { loadUrl(it) }
            ?: article?.url?.let {loadUrl(it) }
    }

    private fun loadUrl(url: String) {
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }
        webView.loadUrl(url)

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                progressBar.progress = 0
                progressBar.visibility = View.VISIBLE
                setProgress(progress * 1000)
                progressBar.incrementProgressBy(progress)
                if (progress == 100) {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}