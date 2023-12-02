// Contributed by Pui Yee Ng
package com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository

class ViewModelFactoryCustomer(private val repository: CustomerRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}