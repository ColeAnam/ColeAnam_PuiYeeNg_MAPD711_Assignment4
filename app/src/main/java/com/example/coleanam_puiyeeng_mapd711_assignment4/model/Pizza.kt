// Contributed by Cole Anam
package com.example.coleanam_puiyeeng_mapd711_assignment4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
// Pizza model
@Entity(tableName = "pizzas")
data class Pizza(
    @PrimaryKey(autoGenerate = true)
    val productId: Long = 0,
    val pizzaName: String,
    val price: Double,
    val category: String
)
