package com.mskinik.products.domain.repository

import com.mskinik.products.data.model.local.Favorite
import com.mskinik.products.data.model.remote.ProductResponse
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.model.Product
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {
    suspend fun getAllProduct(): Resource<List<Product>?>
    suspend fun searchProducts(query: String?): Response<ProductResponse>
    fun getProductById(id: Int): Single<Resource<Product?>>
    suspend fun getFavorites(): Flow<List<Favorite>>
    suspend fun setFavorite(item: Favorite): Long
    suspend fun deleteFavorite(id: String): Int
    suspend fun isFavorite(id: String): Flow<Boolean>
}