package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.CustomerViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactory
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityEditCustomerBinding

class EditCustomerActivity : AppCompatActivity() {

    private lateinit var viewModel: CustomerViewModel

    private lateinit var binding: ActivityEditCustomerBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)

        val repository =
            CustomerRepository(CustomerDatabase.getDatabaseInstance(applicationContext).customerDao())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CustomerViewModel::class.java]

    }
    private suspend fun updateCustomer() {
        val newUsername = binding.newUsername.text.toString()
        val newPassword = binding.newPassword.text.toString()
        val newFirstName = binding.newFirstName.text.toString()
        val newLastName = binding.newLastName.text.toString()
        val newAddress = binding.newAddress.text.toString()
        val newCity = binding.newCity.text.toString()
        val newPostalCode = binding.newPostalCode.text.toString()

        val username = sharedPreferences.getString("username", "")

        if (username != null) {

            // val customer = Customer(userName = newUsername, password = newPassword, firstname = newFirstName, lastName = newLastName, address = newAddress, city = newCity, postalCode = newPostalCode)
            var selectedCustomer = viewModel.getCustomerByUsername(username)

            if (newUsername != "") {
                if (selectedCustomer != null) {
                    selectedCustomer.userName = newUsername
                }
            }
            if (newPassword != "") {
                if (selectedCustomer != null) {
                    selectedCustomer.password = newPassword
                }
            }
            if (newFirstName != "") {
                if (selectedCustomer != null) {
                    selectedCustomer.firstname = newFirstName
                }
            }
            if (newLastName != "") {
                if (selectedCustomer != null) {
                    selectedCustomer.lastName = newLastName
                }
            }
            if (newAddress != "") {
                if (selectedCustomer != null) {
                    selectedCustomer.address = newAddress
                }
            }


            if (selectedCustomer != null) {
                viewModel.updateCustomer(selectedCustomer)
            }
        }

        binding.newUsername.text.clear()
        binding.newPassword.text.clear()
        binding.newFirstName.text.clear()
        binding.newLastName.text.clear()
        binding.newAddress.text.clear()
        binding.newCity.text.clear()
        binding.newPostalCode.text.clear()
    }

}

