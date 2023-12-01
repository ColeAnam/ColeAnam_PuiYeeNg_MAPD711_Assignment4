package com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.AdminRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Admin
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AdminViewModel (
    private val adminRepository: AdminRepository
    ) : ViewModel() {

        private val _admins = MutableLiveData<List<Admin>>()
        private val _admin = MutableLiveData<Admin>()

        val admins: LiveData<List<Admin>>
        get() = _admins

        val customer: LiveData<Admin>
        get() = _admin

        fun adminLogin(username: String, password: String): Boolean {
            val admin = runBlocking { adminRepository.getAdminByUsername(username) }
            return admin?.password == password
        }

        suspend fun getAllAdmins(): List<Admin> {
            return adminRepository.getAllAdmins()
        }

        suspend fun getAdminByUsername(username:String) : Admin? {
            return  adminRepository.getAdminByUsername(username)
        }

        suspend fun getAdminByEmployeeId(employeeId:Long) : Admin? {
            return  adminRepository.getAdminByEmployeeId(employeeId)
        }

        fun insertAdmin(admin: Admin) {
            viewModelScope.launch {
                adminRepository.insertAdmin(admin)
            }
        }

        suspend fun updateAdmin(customer: Admin) {
            viewModelScope.launch {
                adminRepository.updateAdmin(customer)
            }
        }

        fun deleteAdmin(admin: Admin) {
            viewModelScope.launch {
                adminRepository.deleteAdmin(admin)
            }
        }

        fun deleteAll() {
            viewModelScope.launch {
                adminRepository.deleteAll()
            }
        }
}
