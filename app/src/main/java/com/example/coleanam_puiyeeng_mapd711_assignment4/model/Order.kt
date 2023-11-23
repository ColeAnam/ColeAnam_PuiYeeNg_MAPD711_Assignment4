package com.example.coleanam_puiyeeng_mapd711_assignment4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0,
    val customerId: Int?,
    val productId: Int?,
    val employeeId: Int?,
    val orderDate: String?,
    val quantity: Int?,
    val status: String?
)
