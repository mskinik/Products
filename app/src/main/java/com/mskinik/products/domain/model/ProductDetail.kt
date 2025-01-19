package com.mskinik.products.domain.model

import com.mskinik.products.data.model.local.CheckoutEntity
import com.mskinik.util.orZero

data class ProductDetail(
    val id: Int?,
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
        id = id.orZero(),
        title = title,
        desc = desc,
        price = price,
        image = image,
        stock = stock,
        quantity = quantity,
    )
}
