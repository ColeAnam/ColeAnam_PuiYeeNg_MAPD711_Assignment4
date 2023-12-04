package com.example.coleanam_puiyeeng_mapd711_assignment4.db

import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Admin
// Admin Repository
class AdminRepository(
    private val adminDao: AdminDao
) {

    suspend fun insertAdmin(admin: Admin){
        adminDao.insertAdmin(admin)
    }

    suspend fun getAllAdmins(): List<Admin>{
        return adminDao.getAllAdmins()
    }

    suspend fun getAdminByUsername(username: String) : Admin? {
        return adminDao.getAdminByUsername(username)
    }

    suspend fun getAdminByEmployeeId(employeeId: Long) : Admin? {
        return adminDao.getAdminByEmployeeId(employeeId)
    }

    suspend fun updateAdmin(customer: Admin) {
        adminDao.updateAdmin(customer)
    }

    suspend fun deleteAdmin(customer: Admin) {
        adminDao.deleteAdmin(customer)
    }

    suspend fun deleteAll() {
        adminDao.deleteAll()
    }

}