// Contributed by Pui Yee Ng
package com.example.coleanam_puiyeeng_mapd711_assignment4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Long = 0,
    var userName: String,
    var password: String,
    var firstname: String,
    var lastName: String,
    var address: String,
    var city: String,
    var postalCode: String
)
