package com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.AdminRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Admin
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import kotlinx.coroutines.launch

class AdminViewModel (
    private val adminRepository: AdminRepository
    ) : ViewModel() {

        private val _admins = MutableLiveData<List<Admin>>()
        private val _admin = MutableLiveData<Admin>()

        val customers: LiveData<List<Admin>>
        get() = _admins

        val customer: LiveData<Admin>
        get() = _admin

        suspend fun getAllCustomers(): List<Admin> {
            return adminRepository.getAllAdmins()
        }

        suspend fun getCustomerByUsername(username:String) : Admin? {
            return  adminRepository.getAdminByUsername(username)
        }

        suspend fun getAdminByEmployeeId(employeeId:Long) : Admin? {
            return  adminRepository.getAdminByEmployeeId(employeeId)
        }

        fun insertCustomer(customer: Admin) {
            viewModelScope.launch {
                adminRepository.insertAdmin(customer)
            }
        }

        suspend fun updateCustomer(customer: Admin) {
            viewModelScope.launch {
                adminRepository.updateAdmin(customer)
            }
        }

        fun deleteUser(customer: Admin) {
            viewModelScope.launch {
                adminRepository.deleteAdmin(customer)
            }
        }

        fun deleteAll() {
            viewModelScope.launch {
                adminRepository.deleteAll()
            }
        }
}
