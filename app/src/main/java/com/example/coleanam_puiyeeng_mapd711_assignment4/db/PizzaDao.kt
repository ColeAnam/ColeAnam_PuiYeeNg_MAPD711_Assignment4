package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Pizza

@Dao
interface PizzaDao {
    // create pizza
    @Insert
    suspend fun insertPizza(pizza: Pizza)

    @Query("SELECT * FROM pizzas")
    fun getAllPizzas(): List<Pizza>
}