// Contributed by Cole Anam and Pui Yee Ng
package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityOrderBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.PizzaDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.PizzaRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.PizzaViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryPizza
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Pizza
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.CustomerViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryCustomer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var customerViewModel: CustomerViewModel

    private lateinit var viewModel: PizzaViewModel

    var customer: Customer? = null
    var checkedinUsername: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        checkedinUsername = sharedPreferences.getString("customer_username", "") ?: ""
        println("Share Pref")
        println(checkedinUsername)
        println("---")

        val repository =
            PizzaRepository(PizzaDatabase.getDatabaseInstance(applicationContext).pizzaDao())
        val viewModelFactoryPizza = ViewModelFactoryPizza(repository)
        viewModel = ViewModelProvider(this, viewModelFactoryPizza)[PizzaViewModel::class.java]

        val customerRepository =
            CustomerRepository(
                CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            )
        val viewModelFactoryCustomer = ViewModelFactoryCustomer(customerRepository)
        customerViewModel =
            ViewModelProvider(this, viewModelFactoryCustomer)[CustomerViewModel::class.java]

        loadPizzas()

        customerViewModel = ViewModelProvider(this, viewModelFactoryCustomer)[CustomerViewModel::class.java]

        binding.buttonPep.setOnClickListener {


                //testData()

                startActivity(Intent(this, InfoActivity::class.java))
            }

        binding.buttonDeluxe.setOnClickListener {
                startActivity(Intent(this, InfoActivity::class.java))
        }

        binding.buttonMeat.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
        }

        binding.customerInfoFab.setOnClickListener {
                showCustomerProfileDialog(it)
        }
    }

    private fun testData() {
        val pizzaName = "Pepperoni"
        val price = 15.99
        val category = "L"

        val pizza = Pizza(pizzaName = pizzaName, price = price, category = category)
        viewModel.insertPizza(pizza)
    }


    private fun loadPizzas() {
        CoroutineScope(Dispatchers.IO).launch {
            val pizzas = viewModel.getAllPizzas()
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    this@OrderActivity,
                    "Total Size ${pizzas.size}",
                    Toast.LENGTH_LONG
                ).show()
            }
            println("Pizzas: $pizzas")
        }
    }

    // Contributed by Pui Yee Ng
    fun showCustomerProfileDialog(view: View) {
        val builder = AlertDialog.Builder(this)

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.profile_order_history_dialog_layout, null)
        val username = dialogLayout.findViewById<TextView>(R.id.username)
        val firstName = dialogLayout.findViewById<TextView>(R.id.firstName)
        val lastName = dialogLayout.findViewById<TextView>(R.id.lastName)
        val address = dialogLayout.findViewById<TextView>(R.id.address)
        val city = dialogLayout.findViewById<TextView>(R.id.city)
        val postalCode = dialogLayout.findViewById<TextView>(R.id.postalCode)

        val editProfileButton = dialogLayout.findViewById<Button>(R.id.editProfile)
        builder.setView(dialogLayout)


        customer = customerViewModel.getCustomerByUsernameResult(checkedinUsername)

        username.text = checkedinUsername
        firstName.text = customer?.firstname
        lastName.text = customer?.lastName
        address.text = customer?.address
        city.text = customer?.city
        postalCode.text = customer?.postalCode

        print(checkedinUsername)
        print(customer?.firstname)

        editProfileButton.setOnClickListener(){
            startActivity(Intent(this, EditCustomerActivity::class.java))
        }

        builder.setPositiveButton("Go Back") { dialogInterface, i ->
            // Go Back to the Order Screen
            print("Go Back Clicked")
        }

        builder.show()
    }

}