package com.mskinik.products.domain.model


data class Checkout (
    val id: String,
    val title: String?,
    val desc: String?,
    val price: Double?,
    val image: String?,
    val stock: Int?,
    val quantity: Int?,
)