package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityOrderBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.PizzaDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.PizzaRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.PizzaViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryPizza
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Pizza
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var viewModel: PizzaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = PizzaRepository(PizzaDatabase.getDatabaseInstance(applicationContext).pizzaDao())
        val viewModelFactoryPizza = ViewModelFactoryPizza(repository)
        viewModel = ViewModelProvider(this, viewModelFactoryPizza)[PizzaViewModel::class.java]

        loadPizzas()

        binding.buttonPep.setOnClickListener{

            //testData()

            startActivity(Intent(this, InfoActivity::class.java))
        }

        binding.buttonDeluxe.setOnClickListener{


            startActivity(Intent(this, InfoActivity::class.java))
        }

        binding.buttonMeat.setOnClickListener{


            startActivity(Intent(this, InfoActivity::class.java))
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
}