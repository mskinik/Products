package com.mskinik.products.data

import com.mskinik.products.data.model.local.FavoriteEntity
import com.mskinik.products.domain.model.Favorite

class TestFavoriteUseCase {
    val favorite = Favorite(
        id = 1,
        title = "title",
        desc = "desc",
        price = 1.0,
        image = "image",
        stock = 1,
    )
    val favoriteList = listOf(favorite)

    val favoriteEntity = FavoriteEntity(
        id = 1,
        title = "title",
        desc = "desc",
        price = 1.0,
        image = "image",
        stock = 1,
    )
}