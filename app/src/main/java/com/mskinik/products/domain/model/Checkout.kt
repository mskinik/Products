package com.mskinik.products.domain.model


data class Checkout (
    val id: Int?,
    val title: String?,
    val desc: String?,
    val price: Double?,
    val image: String?,
    val stock: Int?,
    val quantity: Int?,
)

fun Checkout.toProductDetail() = ProductDetail(
    id = id,
    title = title,
    desc = desc,
    price = price,
    image = image,
    stock = stock,
    quantity = quantity
)