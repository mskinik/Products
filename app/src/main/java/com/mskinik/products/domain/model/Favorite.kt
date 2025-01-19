package com.mskinik.products.domain.model


data class Favorite (
    val id: Int?,
    val title: String?,
    val desc: String?,
    val price: Double?,
    val image: String?,
    val stock: Int?,
)

fun Favorite.toMyProduct(): ProductDetail = ProductDetail(
    id = id,
    title = title,
    desc = desc,
    price = price,
    image = image,
    stock = stock,
    quantity = null,
    )