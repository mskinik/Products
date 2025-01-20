package com.mskinik.products.data

import com.mskinik.products.domain.model.Checkout
import com.mskinik.products.domain.model.ProductDetail

class TestCheckoutUseCase {
    val testProductDetail = ProductDetail(
        id = 1,
        title = "Product 1",
        price = 1.0,
        image = "",
        quantity = 1,
        desc = "",
        category = "",
        stock = 1
    )
   val testProductDetailList = listOf(
       testProductDetail,
        ProductDetail(
            id = 2,
            title = "Product 2",
            price = 2.0,
            image = "",
            quantity = 2,
            desc = "",
            category = "",
            stock = 2
        ),
        ProductDetail(
            id = 3,
            title = "Product 3",
            price = 3.0,
            image = "",
            quantity = 3,
            desc = "",
            category = "",
            stock = 3
        )
    )
    val testCheckout = Checkout(
        id = 1,
        title = "title",
        price = 4.0,
        image = "",
        quantity = 2,
        desc = "",
        stock = 3
    )

    val testCheckoutList = listOf(testCheckout)
}