package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityLoginBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.CustomerViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: CustomerViewModel

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        val repository =
            CustomerRepository(CustomerDatabase.getDatabaseInstance(applicationContext).customerDao())
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CustomerViewModel::class.java]

        val username = binding.editTextUsername.text.toString()
        var password = binding.editTextTextPassword.text.toString()

        // Login button click
        binding.buttonLogin.setOnClickListener{
            editor.putString("customer_username", username).apply()
            editor.putString("customer_password", password).apply()

            if (binding.editTextUsername.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty $username", Toast.LENGTH_SHORT).show()
            }
            else if (binding.editTextTextPassword.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }


        binding.addCustomerFab.setOnClickListener {
            showDialog(it)
        }

        // Admin button click
        binding.buttonAdmin.setOnClickListener{
            editor.putString("admin_username", username).apply()
            editor.putString("admin_password", password).apply()

            if (binding.editTextUsername.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty", Toast.LENGTH_SHORT).show()
            }
            else if (binding.editTextTextPassword.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, OrderStatusActivity::class.java))
            }
        }
    }
    fun showDialog(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Details")

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.registration_dialog_layout, null)
        val username = dialogLayout.findViewById<EditText>(R.id.username)
        val password = dialogLayout.findViewById<EditText>(R.id.password)
        val firstName = dialogLayout.findViewById<EditText>(R.id.firstName)
        val lastName = dialogLayout.findViewById<EditText>(R.id.lastName)
        val address = dialogLayout.findViewById<EditText>(R.id.address)
        val city = dialogLayout.findViewById<EditText>(R.id.city)
        val postalCode = dialogLayout.findViewById<EditText>(R.id.postalCode)
        builder.setView(dialogLayout)

        builder.setPositiveButton("Submit") { dialogInterface, i ->
            // Handle submit button click
            val newUsername = username.text.toString()
            val newPassword = password.text.toString()
            val newFirstName = firstName.text.toString()
            val newLastName = lastName.text.toString()
            val newAddress = address.text.toString()
            val newCity = city.text.toString()
            val newPostalCode = postalCode.text.toString()

            val customer = Customer(userName = newUsername, password = newPassword, firstname = newFirstName, lastName = newLastName, address = newAddress, city = newCity, postalCode = newPostalCode)
            viewModel.insertCustomer(customer)

            username.text.clear()
            password.text.clear()
            firstName.text.clear()
            lastName.text.clear()
            address.text.clear()
            city.text.clear()
            postalCode.text.clear()
        }

        builder.setNegativeButton("Cancel") { dialogInterface, i ->
            // Handle cancel button click
        }
        builder.show()
    }
}