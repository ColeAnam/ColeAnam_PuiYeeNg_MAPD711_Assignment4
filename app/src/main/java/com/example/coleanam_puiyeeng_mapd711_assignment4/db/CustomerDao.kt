package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
// Customer Dao
@Dao
interface CustomerDao {
    //create customer
    @Insert
    suspend fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM customers")
    fun getAllCustomers(): List<Customer>

    @Query("SELECT * FROM customers WHERE userName = :username LIMIT 1")
    suspend fun getCustomerByUsername(username: String): Customer?

    @Query("SELECT * FROM customers WHERE customerId = :customerId LIMIT 1")
    suspend fun getCustomerByCustomerId(customerId: Long): Customer?

    //update user
    @Update
    suspend fun updateCustomer(customer: Customer)

    //delete user
    @Delete
    suspend fun deleteCustomer(customer: Customer)

    //deleteAll
    @Query("DELETE FROM customers")
    suspend fun deleteAll()

}