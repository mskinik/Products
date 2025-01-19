package com.mskinik.products.domain.datasource

import com.mskinik.products.data.model.local.Favorite
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    suspend fun getFavorites(): Flow<List<Favorite>>
    suspend fun addToFavorite(favorite: Favorite): Long
    suspend fun deleteFavorite(id: String): Int
    suspend fun isFavorite(id: String): Flow<Boolean>
}