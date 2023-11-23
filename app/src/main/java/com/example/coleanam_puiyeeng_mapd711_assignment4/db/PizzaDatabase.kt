package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Pizza

@Database(entities = [Pizza::class], version = 1)
abstract class PizzaDatabase : RoomDatabase() {
    abstract fun pizzaDao(): PizzaDao

    companion object {
        private var INSTANCE: PizzaDatabase? = null

        fun getDatabaseInstance(context: Context): PizzaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PizzaDatabase::class.java,
                    "pizza_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}