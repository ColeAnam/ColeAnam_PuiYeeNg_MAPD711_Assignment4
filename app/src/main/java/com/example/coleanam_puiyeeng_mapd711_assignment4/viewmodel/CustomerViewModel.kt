package com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class
CustomerViewModel(
    private val customerRepository: CustomerRepository
) : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>()
    private val _customer = MutableLiveData<Customer>()

    val customers: LiveData<List<Customer>>
        get() = _customers

    val customer: LiveData<Customer>
        get() = _customer


    suspend fun getAllCustomers(): List<Customer> {
        return customerRepository.getAllCustomers()
    }

    fun customerLogin(username: String, password: String): Boolean {
        val customer = runBlocking { customerRepository.getCustomerByUsername(username) }
        return customer?.password == password
    }

    suspend fun getCustomerByUsername(username:String) : Customer? {
        return  customerRepository.getCustomerByUsername(username)
    }

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch {
            customerRepository.insertCustomer(customer)
        }
    }

   suspend fun updateCustomer(customer: Customer) {
       viewModelScope.launch {
           customerRepository.updateCustomer(customer)
       }
   }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch {
            customerRepository.deleteCustomer(customer)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            customerRepository.deleteAll()
        }
    }
}