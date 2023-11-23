package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order

@Dao
interface OrderDao {
    // create order
    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM orders")
    fun getAllOrders(): List<Order>

    @Update
    suspend fun updateOrder(order: Order)
}