package com.mskinik.products.domain.usecase

import com.mskinik.products.data.model.local.FavoriteEntity
import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.products.domain.model.toMyProduct
import com.mskinik.products.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun addFavorite(favoriteEntity: FavoriteEntity) =
        productRepository.setFavorite(favoriteEntity)

    suspend fun getFavorites() = productRepository.getFavorites()

    suspend fun deleteFavorite(id: Int) = productRepository.deleteFavorite(id)

    suspend fun isFavorite(id: Int) = productRepository.isFavorite(id)

    suspend fun getMyProducts(): Flow<List<ProductDetail>> {
        val favoritesFlow = productRepository.getFavorites()
        val checkoutsFlow = productRepository.getCheckouts()

        return combine(favoritesFlow, checkoutsFlow) { favorites, checkouts ->
            favorites.map { it.toMyProduct() }.map { productDetail ->
                val checkout = checkouts.find { it.id == productDetail.id }
                productDetail.copy(quantity = checkout?.quantity)
            }
        }
    }
}