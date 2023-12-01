package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Admin

@Database(entities = [Admin::class], version = 1)
abstract class AdminDatabase : RoomDatabase() {
    abstract fun adminDao(): AdminDao

    companion object {
        private var INSTANCE: AdminDatabase? = null

        fun getDatabaseInstance(context: Context): AdminDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AdminDatabase::class.java,
                    "admin_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}