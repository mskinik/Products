package com.mskinik.products.domain.usecase

import com.mskinik.products.domain.model.ProductDetail
import com.mskinik.products.domain.model.toCheckoutEntity
import com.mskinik.products.domain.model.toProductDetail
import com.mskinik.products.domain.repository.ProductRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun addCheckout(productDetail: ProductDetail) {
        productRepository.addCheckout(productDetail.toCheckoutEntity())
    }

    suspend fun deleteCheckout(id: Int) {
        productRepository.deleteCheckout(id)
    }

    suspend fun increaseQuantity(id: Int) {
        productRepository.increaseQuantity(id)
    }

    suspend fun decreaseQuantity(id: Int) {
        productRepository.decreaseQuantity(id)
    }

    suspend fun getCheckouts() =
        productRepository.getCheckouts().map { it.map { it.toProductDetail() } }

    suspend fun getTotalQuantity() = productRepository.getTotalQuantity()

    suspend fun deleteAllCheckouts() = productRepository.deleteAllCheckouts()

    suspend fun getCheckoutById(id: Int) =
        productRepository.getCheckoutById(id).map { it?.toProductDetail() }

}