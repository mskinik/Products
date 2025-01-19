package com.mskinik.products.domain.model

import com.mskinik.products.data.model.local.FavoriteEntity

data class Product(
    val availabilityStatus: String?,
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double?,
    val id: Int?,
    val images: List<String>?,
    val previewImage: String?,
    val meta: Meta?,
    val minimumOrderQuantity: Int?,
    val price: Double?,
    val rating: Double?,
    val reviewList: List<Review>?,
    val stock: Int?,
    val tags: List<String>?,
    val thumbnail: String?,
    val title: String?,
)

fun Product.toFavorite(): FavoriteEntity = FavoriteEntity(
    id = id.toString(),
    title = title,
    desc = description,
    price = price,
    image = thumbnail.orEmpty(),
    stock = stock,
)
