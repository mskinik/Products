package com.mskinik.products.domain.datasource

import com.mskinik.products.data.model.local.CheckoutEntity
import com.mskinik.products.data.model.local.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    suspend fun getFavorites(): Flow<List<FavoriteEntity>>
    suspend fun addToFavorite(favoriteEntity: FavoriteEntity): Long
    suspend fun deleteFavorite(id: Int): Int
    suspend fun isFavorite(id: Int): Flow<Boolean>
    suspend fun getCheckouts(): Flow<List<CheckoutEntity>>
    suspend fun addCheckout(checkoutEntity: CheckoutEntity): Long
    suspend fun deleteCheckout(id: Int): Int
    suspend fun increaseQuantity(id: Int): Int
    suspend fun decreaseQuantity(id: Int): Int
    fun getTotalQuantity(): Flow<Int?>
    fun getCheckoutById(id: Int): Flow<CheckoutEntity?>
    suspend fun deleteAllCheckouts(): Int
}