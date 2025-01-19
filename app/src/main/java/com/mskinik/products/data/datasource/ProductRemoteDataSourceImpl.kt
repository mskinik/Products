package com.mskinik.products.data.datasource

import com.mskinik.products.data.model.ProductDTO
import com.mskinik.products.data.network.ProductService
import com.mskinik.products.data.network.Resource
import com.mskinik.products.domain.datasource.ProductRemoteDataSource
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
    private val productService: ProductService
) : ProductRemoteDataSource {
    override suspend fun getAllProduct() = productService.getAllProduct()

    override suspend fun searchProducts(query: String?) = productService.searchProducts(query)
    override fun getProductById(id: Int) = productService.getProductById(id)
}