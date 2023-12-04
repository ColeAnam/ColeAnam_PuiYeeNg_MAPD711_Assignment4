package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order

@Dao
interface OrderDao {
    // create order
    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM orders")
    fun getAllOrders(): List<Order>

    @Update
    suspend fun updateOrder(order: Order?)

    @Query("SELECT * FROM orders WHERE orderId = :orderId")
    fun getOrderById(orderId: Int): Order?

    @Query("SELECT * FROM orders WHERE customerId = :customerId")
    fun getOrderByCustomer(customerId: Int): Order?

    @Delete
    suspend fun deleteOrder(order: Order)

    @Query("DELETE FROM orders")
    suspend fun deleteAll()
}