package com.example.coleanam_puiyeeng_mapd711_assignment4.dbo

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Pizza

@Dao
interface PizzaDao {
    // create pizza
    @Insert
    suspend fun insertPizza(pizza: Pizza)

    @Query("SELECT * FROM pizzas")
    fun getAllUsers(): List<Pizza>
}