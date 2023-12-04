package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order
// Order Database
@Database(entities = [Order::class], version = 1)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object{
        private var INSTANCE: OrderDatabase? = null

        fun getDatabaseInstance(context: Context): OrderDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderDatabase::class.java,
                    "order_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}