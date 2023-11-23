package com.example.coleanam_puiyeeng_mapd711_assignment4.dbo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactoryPizza(private val repository: PizzaRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PizzaViewModel::class.java)) {
            return PizzaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}