package com.mskinik.products.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite (@PrimaryKey val id: String, val title: String?, val desc: String?, val price: Double?, val image: String?)