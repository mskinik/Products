package com.mskinik.products.domain.datasource

import com.mskinik.products.data.model.local.CheckoutEntity
import com.mskinik.products.data.model.local.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    suspend fun getFavorites(): Flow<List<FavoriteEntity>>
    suspend fun addToFavorite(favoriteEntity: FavoriteEntity): Long
    suspend fun deleteFavorite(id: String): Int
    suspend fun isFavorite(id: String): Flow<Boolean>
    suspend fun getCheckouts(): Flow<List<CheckoutEntity>>
    suspend fun addCheckout(checkoutEntity: CheckoutEntity): Long
    suspend fun deleteCheckout(id: String): Int
    suspend fun increaseQuantity(id: String): Int
    suspend fun decreaseQuantity(id: String): Int
}