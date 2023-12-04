package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityInfoBinding
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.CustomerRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.OrderDatabase
import com.example.coleanam_puiyeeng_mapd711_assignment4.db.OrderRepository
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Customer
import com.example.coleanam_puiyeeng_mapd711_assignment4.model.Order
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.CustomerViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.OrderViewModel
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryCustomer
import com.example.coleanam_puiyeeng_mapd711_assignment4.viewmodel.ViewModelFactoryOrder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var customerViewModel: CustomerViewModel
    private lateinit var orderViewModel: OrderViewModel

    var customer: Customer? = null
    var order: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)

        val customerRepository =
            CustomerRepository(
                CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            )
        val viewModelFactoryCustomer = ViewModelFactoryCustomer(customerRepository)
        customerViewModel =
            ViewModelProvider(this, viewModelFactoryCustomer)[CustomerViewModel::class.java]

        val orderRepository = OrderRepository(
            OrderDatabase.getDatabaseInstance(applicationContext).orderDao()
        )
        val viewModelFactoryOrder = ViewModelFactoryOrder(orderRepository)
        orderViewModel = ViewModelProvider(this, viewModelFactoryOrder)[OrderViewModel::class.java]

        val customerUsername = sharedPreferences.getString("customer_username", "") ?: ""
        val orderId = sharedPreferences.getString("customer_order", "")

        CoroutineScope(Dispatchers.IO).launch {
            customer = customerViewModel.getCustomerByUsername(customerUsername)

            order = customer?.customerId?.let { orderViewModel.getOrderByCustomer(it.toInt()) }
            println(order?.orderId)

            binding.usernameLabel.text = customer?.userName
            binding.passwordLabel.text = customer?.password
            binding.firstnameLabel.text = customer?.firstname
            binding.lastNameLabel.text = customer?.lastName
            binding.addressLabel.text = customer?.address
            binding.cityLabel.text = customer?.city
            binding.postalCodeLabel.text = customer?.postalCode

            binding.orderIdLabel.text = order?.orderId.toString()
            binding.customerIdLabel.text = order?.customerId.toString()
            binding.productIdLabel.text = order?.productId.toString()
            binding.employeeIdLabel.text = order?.employeeId.toString()
            binding.orderDateLabel.text = order?.orderDate
            binding.quantityLabel.text = order?.quantity.toString()
            binding.statusLabel.text = order?.status
        }

    }

}
