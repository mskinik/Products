package com.mskinik.products.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mskinik.products.domain.model.Favorite

@Entity
data class FavoriteEntity(
    @PrimaryKey val id: Int?,
    val title: String?,
    val desc: String?,
    val price: Double?,
    val image: String?,
    val stock: Int?,
)

fun FavoriteEntity.toFavorite(): Favorite = Favorite(
    id = id,
    title = title,
    desc = desc,
    price = price,
    image = image,
    stock = stock,
)