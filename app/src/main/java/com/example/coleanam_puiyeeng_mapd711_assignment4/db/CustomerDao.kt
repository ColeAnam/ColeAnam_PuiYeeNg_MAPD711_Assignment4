package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
@Dao
interface CustomerDao {
    //create customer
    @Insert
    fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM customers")
    fun getAllCustomers(): List<Customer>

    @Query("SELECT * FROM customers WHERE username = :username")
    fun getCustomerByUsername(username: String?): Customer

    //update user
    @Update
    fun updateCustomer(customer: Customer)

    //delete user
    @Delete
    fun deleteCustomer(customer: Customer)

    //deleteAll
    @Query("DELETE FROM customers")
    fun deleteAll()

}