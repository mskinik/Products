package com.mskinik.products.data

import com.mskinik.products.domain.model.ProductDetail

class TestCheckoutUseCase {
    val testProductDetail = ProductDetail(
        id = 1,
        title = "Product 1",
        price = 1.0,
        image = "https://example.com/image1.jpg",
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
            image = "https://example.com/image2.jpg",
            quantity = 2,
            desc = "",
            category = "",
            stock = 2
        ),
        ProductDetail(
            id = 3,
            title = "Product 3",
            price = 3.0,
            image = "https://example.com/image3.jpg",
            quantity = 3,
            desc = "",
            category = "",
            stock = 3
        )
    )
}