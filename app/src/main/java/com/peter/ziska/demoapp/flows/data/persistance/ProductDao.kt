package com.peter.ziska.demoapp.flows.data.persistance

import androidx.paging.PagingSource
import androidx.room.*
import com.peter.ziska.demoapp.flows.data.persistance.model.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: List<ProductEntity>)

    @Query("SELECT * FROM products")
    fun getPagingSourceProducts(): PagingSource<Int, ProductEntity>

    @Query("SELECT * FROM products")
    fun getProducts(): List<ProductEntity>

    @Query("DELETE FROM products")
    fun clear()

    @Update
    fun updateProduct(product: ProductEntity)
}