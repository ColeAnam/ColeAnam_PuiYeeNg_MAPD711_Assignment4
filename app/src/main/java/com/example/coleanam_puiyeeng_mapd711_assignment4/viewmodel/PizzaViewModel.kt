package com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.PizzaRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Pizza
import kotlinx.coroutines.launch

class PizzaViewModel(private val pizzaRepository: PizzaRepository) : ViewModel() {

    private val _pizza = MutableLiveData<List<Pizza>>()
    val pizzas: LiveData<List<Pizza>>
        get() = _pizza

    fun getAllPizzas(): List<Pizza> {
        return pizzaRepository.getAllPizzas()
    }

    fun insertPizza(pizza: Pizza) {
        viewModelScope.launch{
            pizzaRepository.insertPizza(pizza)
        }
    }
}