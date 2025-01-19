package com.mskinik.products.domain.usecase

import com.mskinik.products.data.model.local.Favorite
import com.mskinik.products.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend fun addFavorite(favorite: Favorite) = repository.setFavorite(favorite)
    suspend fun getFavorites() = repository.getFavorites()
    suspend fun deleteFavorite(id: String) = repository.deleteFavorite(id)
    suspend fun isFavorite(id: String) = repository.isFavorite(id)
    suspend fun clickFavorite(favorite: Favorite, isFavorite: Boolean): Flow<Boolean> {
        return flow {
            if (isFavorite) {
                val result = deleteFavorite(favorite.id)
                if (result != null && result.toInt() != 0) {
                    emit(true)
                } else {
                    emit(false)
                }
            }
            else {
                val result = addFavorite(favorite)
                if (result != null && result.toInt() != -1) {
                    emit(true)
                }
                else
                    emit(false)
            }
        }
    }
}