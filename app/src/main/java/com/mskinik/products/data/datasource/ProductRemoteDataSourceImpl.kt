package com.mskinik.products.data.datasource

import com.mskinik.products.data.network.ProductService
import com.mskinik.products.domain.datasource.ProductRemoteDataSource
import javax.inject.Inject
import javax.inject.Named

class ProductRemoteDataSourceImpl @Inject constructor(
    @Named("regular") private val productService: ProductService,
    @Named("rx") private val productServiceRx: ProductService
) : ProductRemoteDataSource {
    override suspend fun getAllProduct() = productService.getAllProduct()

    override suspend fun searchProducts(query: String?) = productService.searchProducts(query)
    override fun getProductById(id: Int) = productServiceRx.getProductById(id)
}