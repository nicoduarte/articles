package com.nicoduarte.articles.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nicoduarte.articles.database.ArticleDao
import com.nicoduarte.articles.database.ArticleDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    private lateinit var database: ArticleDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    @Throws(Exception::class)
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            ArticleDatabase::class.java
        )
            // allowing main thread queries, just for testing
            .allowMainThreadQueries()
            .build()

        articleDao = database.articleDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        database.close()
    }


    @Test
    @Throws(InterruptedException::class)
    fun getArticlesAfterInserted() {
        // When inserting articles in the data source
        articleDao.insertAll(TestData.ARTICLES)
            .subscribe {
                // When subscribing to the emissions of the articles
                articleDao.getArticles()
                    .test()
                    .assertValue { it.size ==  TestData.ARTICLES.size}
            }
    }


}