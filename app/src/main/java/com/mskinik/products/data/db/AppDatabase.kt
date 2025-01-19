package com.mskinik.products.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mskinik.products.data.db.dao.CheckoutDao
import com.mskinik.products.data.db.dao.FavoriteDao
import com.mskinik.products.data.model.local.CheckoutEntity
import com.mskinik.products.data.model.local.FavoriteEntity

@Database(entities = [FavoriteEntity::class, CheckoutEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val favoritesDao: FavoriteDao
    abstract val checkoutsDao: CheckoutDao
    companion object {
        const val DATABASE_NAME = "my_db"
    }
}