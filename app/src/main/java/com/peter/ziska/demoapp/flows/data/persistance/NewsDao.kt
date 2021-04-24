package com.peter.ziska.demoapp.flows.data.persistance

import androidx.paging.PagingSource
import androidx.room.*
import com.peter.ziska.demoapp.flows.data.persistance.model.ArticleEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: List<ArticleEntity>)

    @Query("SELECT * FROM article")
    fun getArticleByQuery(): PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM article")
    fun clear()

}