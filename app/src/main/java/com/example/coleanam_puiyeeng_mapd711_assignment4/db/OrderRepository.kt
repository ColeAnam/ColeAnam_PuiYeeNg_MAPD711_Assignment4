package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order
import org.w3c.dom.Entity

class OrderRepository(private val orderDao: OrderDao) {

    suspend fun insertOrder(order: Order){
        orderDao.insertOrder(order)
    }

    suspend fun getAllOrders(): List<Order>{
        return orderDao.getAllOrders()
    }

    suspend fun updateOrder(order: Order?) {
        orderDao.updateOrder(order)
    }

    fun getOrderById(orderId: Int): Order? {
        return orderDao.getOrderById(orderId)
    }

    suspend fun deleteOrder(order: Order) {
        orderDao.deleteOrder(order)
    }

    suspend fun deleteAll() {
        orderDao.deleteAll()
    }
}