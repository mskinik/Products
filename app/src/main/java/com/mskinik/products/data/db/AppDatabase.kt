package com.mskinik.products.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mskinik.products.data.db.dao.CheckoutDao
import com.mskinik.products.data.db.dao.FavoriteDao
import com.mskinik.products.data.model.local.Checkout
import com.mskinik.products.data.model.local.Favorite

@Database(entities = [Favorite::class, Checkout::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val favoritesDao: FavoriteDao
    abstract val checkoutsDao: CheckoutDao
    companion object {
        const val DATABASE_NAME = "my_db"
    }
}