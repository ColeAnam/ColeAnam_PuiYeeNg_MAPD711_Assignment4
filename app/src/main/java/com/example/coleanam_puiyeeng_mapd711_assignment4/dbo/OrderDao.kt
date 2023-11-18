package com.example.coleanam_puiyeeng_mapd711_assignment4.dbo

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order

@Dao
interface OrderDao {
    // create order
    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM orders")
    fun getAllUsers(): List<Order>
}