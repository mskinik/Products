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

    suspend fun deleteCheckout(id: String) {
        productRepository.deleteCheckout(id)
    }

    suspend fun increaseQuantity(id: String) {
        productRepository.increaseQuantity(id)
    }

    suspend fun decreaseQuantity(id: String) {
        productRepository.decreaseQuantity(id)
    }

    suspend fun getCheckouts() = productRepository.getCheckouts().map { it.map { it.toProductDetail() } }

    suspend fun getTotalQuantity() = productRepository.getTotalQuantity()
}