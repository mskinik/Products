package com.mskinik.products.domain.usecase

import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.model.Product
import com.mskinik.products.domain.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GetProductDetailUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: Int): Single<Resource<Product?>> {
        return productRepository.getProductById(id)
    }
}