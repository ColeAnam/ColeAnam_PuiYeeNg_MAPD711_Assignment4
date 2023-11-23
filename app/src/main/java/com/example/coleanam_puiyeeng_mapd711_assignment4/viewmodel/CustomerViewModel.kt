package com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import kotlinx.coroutines.launch

class
CustomerViewModel(
    private val customerRepository: CustomerRepository
) : ViewModel() {
//    private val _users = MutableLiveData<List<User>>()
//    val users: LiveData<List<User>>
//        get() = _users
//
//    init {
//        getAllUsers()
//    }

    private val _customers = MutableLiveData<List<Customer>>()
    private val _customer = MutableLiveData<Customer>()

    val customers: LiveData<List<Customer>>
        get() = _customers

    val customer: LiveData<Customer>
        get() = _customer
//    init {
//        getUsers()
//    }

//    private fun getCustomers() {
//        viewModelScope.launch {
//            val result = customerRepository.getAllCustomers()
//            _customers.value = result
//        }
//    }

    suspend fun getAllCustomers(): List<Customer> {
        return customerRepository.getAllCustomers()
//        viewModelScope.launch {
//            val result = userRepository.getAllUsers()
////            _users.value = result
//        }
    }

    suspend fun getCustomerByUsername(username:String) : Customer? {
        return  customerRepository.getCustomerByUsername(username)
//        viewModelScope.launch {

//            val result = customerRepository.getCustomerByUsername(username)
//            _customer.value = result
//        }
//        return _customer.value
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

    fun deleteUser(customer: Customer) {
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