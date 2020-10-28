package com.nicoduarte.articles.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicoduarte.articles.R
import com.nicoduarte.articles.database.model.Article
import com.nicoduarte.articles.ui.utils.getRelativeTime
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
        articleList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int): Article {
        val article = articleList.removeAt(position)
        notifyItemRemoved(position)
        return article
    }

    fun addItem(item: Article, position: Int) {
        articleList[position] = item
        notifyItemInserted(position)
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) = with(itemView) {
            tvTitle.text = article.storyTitle?.let { it } ?: article.title
            tvAuthor.text = context.getString(
                    R.string.author_created_at,
                    article.author,
                    getRelativeTime(article.createdAt)
            )
            setOnClickListener { clickListener(article) }
        }
    }
}