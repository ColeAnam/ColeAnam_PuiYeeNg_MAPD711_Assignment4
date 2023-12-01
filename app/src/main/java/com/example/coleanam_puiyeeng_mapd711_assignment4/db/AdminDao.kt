package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import androidx.room.*
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Admin

@Dao
interface AdminDao {
    //create admin
    @Insert
    suspend fun insertAdmin(admin: Admin)

    @Query("SELECT * FROM admins")
    fun getAllAdmins(): List<Admin>

    @Query("SELECT * FROM admins WHERE userName = :username LIMIT 1")
    suspend fun getAdminByUsername(username: String): Admin?

    @Query("SELECT * FROM admins WHERE employeeId = :employeeId LIMIT 1")
    suspend fun getAdminByEmployeeId(employeeId: Long): Admin?
    //update user
    @Update
    suspend fun updateAdmin(admin: Admin)

    //delete user
    @Delete
    suspend fun deleteAdmin(admin: Admin)

    //deleteAll
    @Query("DELETE FROM admins")
    suspend fun deleteAll()
}