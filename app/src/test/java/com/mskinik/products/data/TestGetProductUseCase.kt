package com.mskinik.products.data

import com.mskinik.products.domain.model.Product
class TestGetProductUseCase {

    val product = Product(
         availabilityStatus = "In Stock",
            brand = "Apple",
            category = "Electronics",
            description = "desc",
            discountPercentage = 10.0,
            id = 1,
            images = listOf("image1", "image2"),
            previewImage = "image1",
            meta = null,
            minimumOrderQuantity = 1,
            price = 1000.0,
            rating = 4.5,
            reviewList = null,
            stock = 10,
            tags = listOf("tag1", "tag2"),
            thumbnail = "thumbnail",
            title = "title",
            weight = 1
     )

    val productList = listOf(product)
}

