// Contributed by Pui Yee Ng
package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.CustomerViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryCustomer
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityEditCustomerBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditCustomerActivity : AppCompatActivity() {

    private lateinit var viewModel: CustomerViewModel

    private lateinit var binding: ActivityEditCustomerBinding
    lateinit var sharedPreferences: SharedPreferences

    var selectedCustomer: Customer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)

        val repository =
            CustomerRepository(
                CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            )
        val viewModelFactoryCustomer = ViewModelFactoryCustomer(repository)
        viewModel = ViewModelProvider(this, viewModelFactoryCustomer)[CustomerViewModel::class.java]


        binding.confirm.setOnClickListener() {

            val newPassword = binding.newPassword.text.toString()
            val newFirstName = binding.newFirstName.text.toString()
            val newLastName = binding.newLastName.text.toString()
            val newAddress = binding.newAddress.text.toString()
            val newCity = binding.newCity.text.toString()
            val newPostalCode = binding.newPostalCode.text.toString()

            val username = sharedPreferences.getString("customer_username", "")
            println(username)

            if (username != null) {
                // val customer = Customer(userName = newUsername, password = newPassword, firstname = newFirstName, lastName = newLastName, address = newAddress, city = newCity, postalCode = newPostalCode)
                CoroutineScope(Dispatchers.IO).launch {
                    selectedCustomer = viewModel.getCustomerByUsernameResult(username)
                }
                var updateFields = mutableListOf<String>()

                println(updateFields.isEmpty())
                    if (selectedCustomer != null) {
                        selectedCustomer!!.userName = username
                    }

                if (newPassword != "") {
                    updateFields.add("Password: ******")
                    if (selectedCustomer != null) {
                        selectedCustomer!!.password = newPassword
                    }
                }
                if (newFirstName != "") {
                    updateFields.add("First Name: " + newFirstName)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.firstname = newFirstName
                    }
                }
                if (newLastName != "") {
                    updateFields.add("Last Name: " + newLastName)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.lastName = newLastName
                    }
                }
                if (newAddress != "") {
                    updateFields.add("Address: " + newAddress)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.address = newAddress
                    }
                }
                if (newCity != "") {
                    updateFields.add("City: " + newCity)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.city = newCity
                    }
                }
                if (newPostalCode != "") {
                    updateFields.add("Postal Code: " + newPostalCode)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.postalCode = newPostalCode
                    }
                }
                println(selectedCustomer)
                println(updateFields)
                //println(!updateFields.isEmpty())
                if (updateFields.isEmpty()) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@EditCustomerActivity,
                            "No input for update!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }else {
                    if (selectedCustomer != null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.updateCustomer(selectedCustomer!!)
                        }
                        val updateFieldString = updateFields.joinToString("\n")
                        lifecycleScope.launch(Dispatchers.Main) {
                            Toast.makeText(
                        this@EditCustomerActivity,
                                "Successfully Updated Profile:\n" + updateFieldString,
                                Toast.LENGTH_LONG
                                ).show()
                        }
                    }
                }
            }

            binding.newPassword.text.clear()
            binding.newFirstName.text.clear()
            binding.newLastName.text.clear()
            binding.newAddress.text.clear()
            binding.newCity.text.clear()
            binding.newPostalCode.text.clear()
        }

        binding.goBack.setOnClickListener() {
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }

}

