package com.mskinik.products.domain.usecase

import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke() = flowOf(productRepository.getAllProduct()).flowOn(Dispatchers.IO)
}