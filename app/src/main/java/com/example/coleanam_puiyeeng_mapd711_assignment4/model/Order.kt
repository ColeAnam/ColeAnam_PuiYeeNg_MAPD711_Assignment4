package com.example.coleanam_puiyeeng_mapd711_assignment4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Long = 0,
    val customerId: Long = 0,
    val productId: Long = 0,
    val employeeId: Long = 0,
    val orderDate: Date,
    val quantity: Int = 0,
    val status: String
)
