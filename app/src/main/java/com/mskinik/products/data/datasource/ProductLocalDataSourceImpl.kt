package com.mskinik.products.data.datasource

import com.mskinik.products.data.db.dao.FavoriteDao
import com.mskinik.products.data.model.local.Favorite
import com.mskinik.products.domain.datasource.ProductLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductLocalDataSourceImpl @Inject constructor(private val favoriteDao: FavoriteDao) :
    ProductLocalDataSource {
    override suspend fun getFavorites() = favoriteDao.getFavorites()
    override suspend fun addToFavorite(favorite: Favorite) = favoriteDao.addFavorite(favorite)
    override suspend fun deleteFavorite(id: String): Int = favoriteDao.deleteFavoriteById(id.toInt())
    override suspend fun isFavorite(id: String): Flow<Boolean> = favoriteDao.isFavorite(id.toInt())
}