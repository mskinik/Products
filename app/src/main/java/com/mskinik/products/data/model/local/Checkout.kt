package com.mskinik.products.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Checkout(@PrimaryKey val id: String, val name: String)
