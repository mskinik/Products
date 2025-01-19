package com.mskinik.products.domain.datasource

import com.mskinik.products.data.model.ProductResponse
import retrofit2.Response

interface ProductRemoteDataSource {
    suspend fun getAllProduct(): Response<ProductResponse>
    suspend fun searchProducts(query: String?): Response<ProductResponse>
}