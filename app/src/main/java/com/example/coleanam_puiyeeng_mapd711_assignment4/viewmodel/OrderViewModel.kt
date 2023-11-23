package com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.dbo.OrderRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order
import kotlinx.coroutines.launch

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {
    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>>
        get() = _orders

    suspend fun getAllOrders(): List<Order> {
        return orderRepository.getAllOrders()
    }

    fun insertOrder(order: Order) {
        viewModelScope.launch {
            orderRepository.insertOrder(order)
        }
    }

    fun updateOrder(order: Order) {
        viewModelScope.launch {
            orderRepository.updateOrder(order)
        }
    }
}