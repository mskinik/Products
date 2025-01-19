package com.mskinik.products.domain.repository

import com.mskinik.products.data.model.ProductDTO
import com.mskinik.products.data.model.ProductResponse
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.model.Product
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {
    suspend fun getAllProduct(): Resource<List<Product>?>
    suspend fun searchProducts(query: String?): Response<ProductResponse>
    fun getProductById(id: Int): Single<Resource<Product?>>}