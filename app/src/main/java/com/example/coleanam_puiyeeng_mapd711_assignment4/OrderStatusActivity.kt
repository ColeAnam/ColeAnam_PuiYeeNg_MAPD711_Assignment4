// Contributed by Cole Anam
package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityOrderStatusBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.OrderDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.OrderRepository
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
    private lateinit var statusUpdateButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository =
            OrderRepository(OrderDatabase.getDatabaseInstance(applicationContext).orderDao())
        val viewModelFactoryOrder = ViewModelFactoryOrder(repository)
        viewModel = ViewModelProvider(this, viewModelFactoryOrder)[OrderViewModel::class.java]
        recycleView = findViewById(R.id.recyclerView)

        statusUpdateButton = binding.buttonUpdateOrderStatus

        //testData()
        //deleteData()
        //viewModel.deleteAll()
        //viewModel.deleteAll()

        loadOrders()

        statusUpdateButton.setOnClickListener{
            showUpdateDialog(it)
        }
    }

    fun showUpdateDialog(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter the Order ID of the order that you want to update")

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
        val etOrderId =dialogLayout.findViewById<EditText>(R.id.et_orderId)
        builder.setView(dialogLayout)

        builder.setPositiveButton("Submit") { dialogInterface, i ->
            CoroutineScope(Dispatchers.IO).launch {
                // Handle submit button click
                val orderId = etOrderId.text.toString().toInt()

                var order: Order? = viewModel.getOrderById(orderId)
                println(order)
                viewModel.updateOrder(order)
                println("Order Update $order")

                etOrderId.text.clear()
            }

            val intent = Intent(this, OrderStatusActivity::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton("Cancel") { dialogInterface, i ->
            // Handle cancel button click
        }
        builder.show()
    }

    private fun testData() {
        val customerId = 1
        val productId = 1
        val employeeId = 1
        val orderDate = "10/12/2023"
        val quantity = 2
        val status = "In-Process"

        val order = Order(customerId = customerId, productId = productId, employeeId = employeeId, orderDate = orderDate, quantity = quantity, status = status)
        viewModel.insertOrder(order)
    }

    private fun deleteData() {
        val orderId = 2
        val customerId = 1
        val productId = 1
        val employeeId = 1
        val orderDate = "10/12/2023"
        val quantity = 2
        val status = "pending"

        val order = Order(orderId = orderId, customerId, productId = productId, employeeId = employeeId, orderDate = orderDate, quantity = quantity, status = status)
        viewModel.deleteOrder(order)
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