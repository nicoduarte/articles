package com.nicoduarte.articles.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicoduarte.articles.R
import com.nicoduarte.articles.database.Article
import com.nicoduarte.articles.ui.utils.getDateFormatted
import com.nicoduarte.articles.ui.utils.inflate
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleAdapter(
    private var articleList: MutableList<Article>,
    private val clickListener: (Article) -> Unit
)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return MovieHolder(parent.inflate(R.layout.item_article))
    }

    override fun getItemCount() = articleList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).bind(articleList[position])
    }

    fun addArticles(list: List<Article>) {
        val positionStart = articleList.size
        articleList.addAll(list)
        notifyItemRangeInserted(positionStart, articleList.size)
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) = with(itemView) {
            tvTitle.text = article.storyTitle?.let { it } ?: article.title
            tvAuthor.text =  context.getString(
                R.string.author_created_at,
                article.author,
                getDateFormatted(article.createdAt)
            )
            clickListener(article)
        }
    }
}