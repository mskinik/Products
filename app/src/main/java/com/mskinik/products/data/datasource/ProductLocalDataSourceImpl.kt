package com.mskinik.products.data.datasource

import com.mskinik.products.data.db.dao.CheckoutDao
import com.mskinik.products.data.db.dao.FavoriteDao
import com.mskinik.products.data.model.local.CheckoutEntity
import com.mskinik.products.data.model.local.FavoriteEntity
import com.mskinik.products.domain.datasource.ProductLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductLocalDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val checkoutDao: CheckoutDao
) :
    ProductLocalDataSource {
    override suspend fun getFavorites() = favoriteDao.getFavorites()
    override suspend fun addToFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.addFavorite(favoriteEntity)
    override suspend fun deleteFavorite(id: String): Int = favoriteDao.deleteFavoriteById(id.toInt())
    override suspend fun isFavorite(id: String): Flow<Boolean> = favoriteDao.isFavorite(id.toInt())
    override suspend fun getCheckouts() = checkoutDao.getCheckouts()
    override suspend fun addCheckout(checkoutEntity: CheckoutEntity) = checkoutDao.addCheckout(checkoutEntity)
    override suspend fun deleteCheckout(id: String) = checkoutDao.deleteCheckoutById(id.toInt())
    override suspend fun increaseQuantity(id: String) = checkoutDao.increaseQuantity(id.toInt())
    override suspend fun decreaseQuantity(id: String) = checkoutDao.decreaseQuantity(id.toInt())
    override fun getTotalQuantity() = checkoutDao.getTotalQuantity()
    override suspend fun deleteAllCheckouts() = checkoutDao.deleteAllCheckouts()
}