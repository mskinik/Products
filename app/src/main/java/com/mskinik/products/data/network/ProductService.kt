package com.mskinik.products.data.network

import com.mskinik.products.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("products")
    suspend fun getAllProduct(): Response<ProductResponse>

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String?): Response<ProductResponse>
}