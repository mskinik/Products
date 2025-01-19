package com.mskinik.products.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mskinik.products.data.model.local.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query(
        "SELECT * FROM favorite"
    )
    fun getFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(item: Favorite): Long

    @Query("SELECT EXISTS(SELECT 1 FROM favorite WHERE id = :id)")
    fun isFavorite(id: Int): Flow<Boolean>

    @Query("DELETE FROM favorite WHERE id = :id")
    fun deleteFavoriteById(id: Int): Int
}