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
import androidx.lifecycle.lifecycleScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityLoginBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.AdminDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.AdminRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Admin
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.AdminViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.CustomerViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryAdmin
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryCustomer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var customerViewModel: CustomerViewModel
    private lateinit var adminViewModel: AdminViewModel

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    var customer: Customer?  = null
    var admin: Admin?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        val customerRepository =
            CustomerRepository(CustomerDatabase.getDatabaseInstance(applicationContext).customerDao())
        val viewModelFactoryCustomer = ViewModelFactoryCustomer(customerRepository)
        customerViewModel = ViewModelProvider(this, viewModelFactoryCustomer)[CustomerViewModel::class.java]

        val adminRepository =
            AdminRepository(AdminDatabase.getDatabaseInstance(applicationContext).adminDao())
        val viewModelFactoryAdmin = ViewModelFactoryAdmin(adminRepository)
        adminViewModel = ViewModelProvider(this, viewModelFactoryAdmin)[AdminViewModel::class.java]

//        val testAdmin = Admin(userName = "hilaryng", password = "admin", firstname = "Hilary", lastName = "Ng")
//        val testAdmin = Admin(userName = "ColeAnam", password = "admin", firstname = "Cole", lastName = "Anam")
//        adminViewModel.insertAdmin(testAdmin)

        // Login button click
        binding.buttonLogin.setOnClickListener{
            var username = binding.editTextUsername.text.toString()
            var password = binding.editTextTextPassword.text.toString()

            if (binding.editTextUsername.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty $username", Toast.LENGTH_SHORT).show()
            }
            else if (binding.editTextTextPassword.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            else {

                CoroutineScope(Dispatchers.IO).launch {
                    val customers =  customerViewModel.getAllCustomers()
                    print("All Customer:")
                    println(customers)
                    customer = customerViewModel.getCustomerByUsername(username)
                    print(customer)

                    println("customer:$customer")
                }
//                if(customer != null) {
//                    val customerPassword = customer?.password
//                    if (password == customerPassword) {
//                        editor.putString("customer_username", customer?.userName).apply()
//                        startActivity(Intent(this, OrderActivity::class.java))
//                    } else {
//                        lifecycleScope.launch(Dispatchers.Main) {
//                            Toast.makeText(
//                                this@LoginActivity,
//                                "Incorrect Username or Password",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    }
//                }
                if (customerViewModel.customerLogin(username, password)) {
                    println()
                    println("Customer TRUE")
                    editor.putString("customer_username", customer?.userName).apply()
                    startActivity(Intent(this, OrderActivity::class.java))
                }
                else {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Incorrect Username or Password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        // Admin button click
        binding.buttonAdmin.setOnClickListener {
            var username = binding.editTextUsername.text.toString()
            var password = binding.editTextTextPassword.text.toString()

            if (binding.editTextUsername.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty", Toast.LENGTH_SHORT).show()
            } else if (binding.editTextTextPassword.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val admins = adminViewModel.getAllAdmins()
                    print("All Admin:")
                    print(admins)
                    admin = adminViewModel.getAdminByUsername(username)
                    println(admin)

                    //println("customer:$admin")
                }


                if (adminViewModel.adminLogin(username, password)) {
                    println()
                    println("Admin TRUE")
                    editor.putString("admin_username", admin?.userName).apply()
                    startActivity(Intent(this, OrderStatusActivity::class.java))
                }
                else {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Incorrect Username or Password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }
        }

        binding.addCustomerFab.setOnClickListener {
            showDialog(it)
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


            CoroutineScope(Dispatchers.IO).launch {
                val checkedCustomer =  customerViewModel.getCustomerByUsername(newUsername)
                if (checkedCustomer != null){
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Username '$newUsername' already exists, please register with another Username",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else{
                    val customer = Customer(userName = newUsername, password = newPassword, firstname = newFirstName, lastName = newLastName, address = newAddress, city = newCity, postalCode = newPostalCode)
                    customerViewModel.insertCustomer(customer)

                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Successfully Registered Account '$newUsername'!",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    username.text.clear()
                    password.text.clear()
                    firstName.text.clear()
                    lastName.text.clear()
                    address.text.clear()
                    city.text.clear()
                    postalCode.text.clear()
                }
            }


        }

        builder.setNegativeButton("Cancel") { dialogInterface, i ->
            // Handle cancel button click
        }
        builder.show()
    }
}