package com.example.coleanam_puiyeeng_mapd711_assignment4.dbo

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
@Dao
interface CustomerDao {
    //create customer
    @Insert
    suspend fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM customers")
    fun getAllUsers(): List<Customer>

}