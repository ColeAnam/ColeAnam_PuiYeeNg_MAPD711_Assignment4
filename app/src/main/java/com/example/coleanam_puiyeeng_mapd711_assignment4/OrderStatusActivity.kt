package com.example.coleanam_puiyeeng_mapd711_assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityOrderStatusBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.dbo.OrderDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.dbo.OrderRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.OrderViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryOrder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderStatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderStatusBinding
    private lateinit var recycleView: RecyclerView
    private lateinit var viewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository =
            OrderRepository(OrderDatabase.getDatabaseInstance(applicationContext).orderDao())
        val viewModelFactoryOrder = ViewModelFactoryOrder(repository)
        viewModel = ViewModelProvider(this, viewModelFactoryOrder)[OrderViewModel::class.java]
        recycleView = findViewById(R.id.recyclerView)

        //testData()

        loadOrders()
    }

    private fun testData() {
        val customerId = 1
        val productId = 1
        val employeeId = 1
        val orderDate = "10/12/2023"
        val quantity = 2
        val status = "pending"

        val order = Order(customerId = customerId, productId = productId, employeeId = employeeId, orderDate = orderDate, quantity = quantity, status = status)
        viewModel.insertOrder(order)
    }

    private fun initRecyclerview() {
        recycleView.layoutManager = LinearLayoutManager(this@OrderStatusActivity)
    }

    private fun loadOrders() {
        CoroutineScope(Dispatchers.IO).launch {
            val orders = viewModel.getAllOrders()
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    this@OrderStatusActivity,
                    "Total Size ${orders.size}",
                    Toast.LENGTH_LONG
                ).show()
                initRecyclerview()
                val adapter = OrderAdapter(orders)
                recycleView.adapter = adapter
            }
        }
    }
}