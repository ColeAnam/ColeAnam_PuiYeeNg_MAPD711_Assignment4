package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Pizza

class PizzaRepository(private val pizzaDao: PizzaDao) {

    suspend fun insertPizza(pizza: Pizza){
        pizzaDao.insertPizza(pizza)
    }

    fun getAllPizzas(): List<Pizza>{
        return pizzaDao.getAllPizzas()
    }
}