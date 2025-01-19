package com.mskinik.products.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mskinik.products.domain.model.Checkout

@Entity
data class CheckoutEntity(
    @PrimaryKey val id: String,
    val title: String?,
    val desc: String?,
    val price: Double?,
    val image: String?,
    val stock: Int?,
    val quantity: Int?,
)

fun CheckoutEntity.toCheckout(): Checkout = Checkout(
    id = id,
    title = title,
    desc = desc,
    price = price,
    image = image,
    stock = stock,
    quantity = quantity,
)
