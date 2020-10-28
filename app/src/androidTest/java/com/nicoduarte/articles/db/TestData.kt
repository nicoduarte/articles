package com.nicoduarte.articles.db


import com.nicoduarte.articles.database.model.Article

class TestData {

    companion object {
        private val ARTICLE_ENTITY = Article(
            "2020-10-28T01:19:57.000Z",
            null,
            null,
            "",
            1,
            "",
            "",1,
            1,
            "",
            "",
            1,
            1,
            "123"

        )
        private val ARTICLE__ENTITY2 = Article(
            "2020-10-28T01:19:57.000Z",
            null,
            null,
            "",
            1,
            "",
            "",1,
            1,
            "",
            "",
            1,
            1,
            "456"
        )

        val ARTICLES = listOf(ARTICLE_ENTITY, ARTICLE__ENTITY2)
    }
}