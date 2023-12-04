package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
// Customer Repository
class CustomerRepository(
    private val customerDao: CustomerDao
) {

    suspend fun insertCustomer(customer: Customer){
        customerDao.insertCustomer(customer)
    }

    suspend fun getAllCustomers(): List<Customer>{
        return customerDao.getAllCustomers()
    }

    suspend fun getCustomerByUsername(username: String) : Customer? {
        return customerDao.getCustomerByUsername(username)
    }

    suspend fun getCustomerByCustomerId(customerId: Long) : Customer? {
        return customerDao.getCustomerByCustomerId(customerId)
    }

    suspend fun updateCustomer(customer: Customer) {
        customerDao.updateCustomer(customer)
    }

    suspend fun deleteCustomer(customer: Customer) {
        customerDao.deleteCustomer(customer)
    }

    suspend fun deleteAll() {
        customerDao.deleteAll()
    }

}