package com.mskinik.products.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mskinik.products.data.model.local.CheckoutEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckoutDao {
    @Query("SELECT * FROM checkoutentity")
    fun getCheckouts(): Flow<List<CheckoutEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCheckout(item: CheckoutEntity): Long

    @Query("SELECT EXISTS(SELECT 1 FROM checkoutentity WHERE id = :id)")
    fun isCheckout(id: Int): Flow<Boolean>

    @Query("DELETE FROM checkoutentity WHERE id = :id")
    suspend fun deleteCheckoutById(id: Int): Int

    @Query("UPDATE checkoutentity SET quantity = quantity + 1 WHERE id = :id")
    suspend fun increaseQuantity(id: Int): Int

    @Query("UPDATE checkoutentity SET quantity = quantity - 1 WHERE id = :id AND quantity > 1")
    suspend fun decreaseQuantity(id: Int): Int
}