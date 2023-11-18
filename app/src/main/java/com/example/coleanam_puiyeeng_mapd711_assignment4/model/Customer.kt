package com.example.coleanam_puiyeeng_mapd711_assignment4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Long = 0,
    val userName: String,
    val password: String,
    val firstname: String,
    val lastName: String,
    val address: String,
    val city: String,
    val postalCode: String
)
