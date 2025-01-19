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
    override suspend fun deleteFavorite(id: Int): Int = favoriteDao.deleteFavoriteById(id)
    override suspend fun isFavorite(id: Int): Flow<Boolean> = favoriteDao.isFavorite(id)
    override suspend fun getCheckouts() = checkoutDao.getCheckouts()
    override suspend fun addCheckout(checkoutEntity: CheckoutEntity) = checkoutDao.addCheckout(checkoutEntity)
    override suspend fun deleteCheckout(id: Int) = checkoutDao.deleteCheckoutById(id)
    override suspend fun increaseQuantity(id: Int) = checkoutDao.increaseQuantity(id)
    override suspend fun decreaseQuantity(id: Int) = checkoutDao.decreaseQuantity(id)
    override fun getTotalQuantity() = checkoutDao.getTotalQuantity()
    override suspend fun deleteAllCheckouts() = checkoutDao.deleteAllCheckouts()
    override fun getCheckoutById(id: Int) = checkoutDao.getCheckoutById(id)
}