package com.mskinik.products.domain.model

import com.mskinik.products.data.model.local.CheckoutEntity

data class ProductDetail(
    val id: String?,
    val title: String?,
    val desc: String?,
    val price: Double?,
    val image: String?,
    val stock: Int?,
    val quantity: Int?,
    val brand: String? = null,
    val category: String? = null,
)

fun ProductDetail.toCheckoutEntity(): CheckoutEntity {
    return CheckoutEntity(
        id = id.orEmpty(),
        title = title,
        desc = desc,
        price = price,
        image = image,
        stock = stock,
        quantity = quantity,
    )
}
