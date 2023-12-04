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

        // Register customer View Model
        val repository =
            CustomerRepository(
                CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            )
        val viewModelFactoryCustomer = ViewModelFactoryCustomer(repository)
        viewModel = ViewModelProvider(this, viewModelFactoryCustomer)[CustomerViewModel::class.java]


        binding.confirm.setOnClickListener() {
            // get the updated customer info from edit text field
            val newPassword = binding.newPassword.text.toString()
            val newFirstName = binding.newFirstName.text.toString()
            val newLastName = binding.newLastName.text.toString()
            val newAddress = binding.newAddress.text.toString()
            val newCity = binding.newCity.text.toString()
            val newPostalCode = binding.newPostalCode.text.toString()

            // get the customer username from shared preferences
            val username = sharedPreferences.getString("customer_username", "")
            println(username)

            if (username != null) {
                // get the login customer record
                CoroutineScope(Dispatchers.IO).launch {
                    selectedCustomer = viewModel.getCustomerByUsernameResult(username)
                }
                // store the updated fields
                var updateFields = mutableListOf<String>()

                // store username to the updateFields
                if (selectedCustomer != null) {
                    selectedCustomer!!.userName = username
                }

                // store newPassword to the updateFields, change the password of user record to newPassword
                if (newPassword != "") {
                    updateFields.add("Password: ******")
                    if (selectedCustomer != null) {
                        selectedCustomer!!.password = newPassword
                    }
                }

                // store newFirstName to the updateFields, change the firstname of user record to newFirstName
                if (newFirstName != "") {
                    updateFields.add("First Name: " + newFirstName)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.firstname = newFirstName
                    }
                }

                // store newLastName to the updateFields, change the lastName of user record to newLastName
                if (newLastName != "") {
                    updateFields.add("Last Name: " + newLastName)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.lastName = newLastName
                    }
                }

                // store newAddress to the updateFields, change the address of user record to newAddress
                if (newAddress != "") {
                    updateFields.add("Address: " + newAddress)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.address = newAddress
                    }
                }

                // store newAddress to the updateFields, change the city of user record to newCity
                if (newCity != "") {
                    updateFields.add("City: " + newCity)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.city = newCity
                    }
                }

                // store newPostalCode to the updateFields, change the Postal Code of user record to newPostalCode
                if (newPostalCode != "") {
                    updateFields.add("Postal Code: " + newPostalCode)
                    if (selectedCustomer != null) {
                        selectedCustomer!!.postalCode = newPostalCode
                    }
                }
                println(selectedCustomer)
                println(updateFields)

                // Alert when no updated fields are entered
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
                        // update custoemr record
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.updateCustomer(selectedCustomer!!)
                        }
                        // Alert box for Successfully Updated Profile
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
            // clear edit text box
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

