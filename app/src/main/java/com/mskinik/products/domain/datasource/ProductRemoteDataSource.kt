package com.mskinik.products.domain.datasource

import com.mskinik.products.data.model.ProductDTO
import com.mskinik.products.data.model.ProductResponse
import com.mskinik.products.data.network.Resource
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface ProductRemoteDataSource {
    suspend fun getAllProduct(): Response<ProductResponse>
    suspend fun searchProducts(query: String?): Response<ProductResponse>
    fun getProductById(id: Int):Single<Response<ProductDTO?>>}