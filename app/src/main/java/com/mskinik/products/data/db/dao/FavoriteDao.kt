package com.mskinik.products.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mskinik.products.data.model.local.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query(
        "SELECT * FROM favoriteentity"
    )
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(item: FavoriteEntity): Long

    @Query("SELECT EXISTS(SELECT 1 FROM favoriteentity WHERE id = :id)")
    fun isFavorite(id: Int): Flow<Boolean>

    @Query("DELETE FROM favoriteentity WHERE id = :id")
    fun deleteFavoriteById(id: Int): Int
}