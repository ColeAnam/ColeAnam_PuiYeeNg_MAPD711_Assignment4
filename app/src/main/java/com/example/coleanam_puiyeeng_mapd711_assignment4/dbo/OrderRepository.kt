package com.example.coleanam_puiyeeng_mapd711_assignment4.dbo

import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order

class OrderRepository(private val orderDao: OrderDao) {

    suspend fun insertOrder(order: Order){
        orderDao.insertOrder(order)
    }

    suspend fun getAllOrders(): List<Order>{
        return orderDao.getAllOrders()
    }

    suspend fun updateOrder(order: Order) {
        orderDao.updateOrder(order)
    }
}