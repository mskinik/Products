package com.mskinik.products.data.model.remote

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val limit: Int?,
    @SerializedName("products") val productList: List<ProductDTO>?,
    val skip: Int?,
    val total: Int?
)