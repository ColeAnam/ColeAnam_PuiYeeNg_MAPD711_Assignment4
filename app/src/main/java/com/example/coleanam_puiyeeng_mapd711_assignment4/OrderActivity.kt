package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityOrderBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.dbo.PizzaDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.dbo.PizzaRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.dbo.PizzaViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.dbo.ViewModelFactoryPizza
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Pizza

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

        binding.buttonPep.setOnClickListener{

            testData()

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

}