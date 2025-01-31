package com.mskinik.products.domain.repository

import com.mskinik.products.data.model.local.CheckoutEntity
import com.mskinik.products.data.model.local.FavoriteEntity
import com.mskinik.products.data.model.remote.ProductResponse
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.model.Checkout
import com.mskinik.products.domain.model.Favorite
import com.mskinik.products.domain.model.Product
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {
    suspend fun getAllProduct(): Resource<List<Product>?>
    suspend fun searchProducts(query: String?): Response<ProductResponse>
    fun getProductById(id: Int): Single<Resource<Product?>>
    suspend fun getFavorites(): Flow<List<Favorite>>
    suspend fun setFavorite(item: FavoriteEntity): Long
    suspend fun deleteFavorite(id: Int): Int
    suspend fun isFavorite(id: Int): Flow<Boolean>
    suspend fun getCheckouts(): Flow<List<Checkout>>
    suspend fun addCheckout(productDetail: CheckoutEntity)
    suspend fun deleteCheckout(id: Int)
    suspend fun decreaseQuantity(id: Int)
    suspend fun increaseQuantity(id: Int)
    suspend fun getTotalQuantity(): Flow<Int?>
    suspend fun deleteAllCheckouts(): Flow<Int>
    suspend fun getCheckoutById(id: Int): Flow<Checkout?>
}