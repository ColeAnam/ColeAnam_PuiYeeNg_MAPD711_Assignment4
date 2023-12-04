// Contributed by Cole Anam
package com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.PizzaRepository
// Pizza View Model Factory
class ViewModelFactoryPizza(private val repository: PizzaRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PizzaViewModel::class.java)) {
            return PizzaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}