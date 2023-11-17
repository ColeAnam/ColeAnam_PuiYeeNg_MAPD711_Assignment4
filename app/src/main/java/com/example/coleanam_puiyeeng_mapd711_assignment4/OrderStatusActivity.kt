package com.example.coleanam_puiyeeng_mapd711_assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityOrderStatusBinding

class OrderStatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderStatusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}