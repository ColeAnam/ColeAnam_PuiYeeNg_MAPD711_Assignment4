package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
// Customer Database
@Database(entities = [Customer::class], version = 1)
abstract class CustomerDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao

    companion object {
        private var INSTANCE: CustomerDatabase? = null

        fun getDatabaseInstance(context: Context): CustomerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerDatabase::class.java,
                    "customer_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}