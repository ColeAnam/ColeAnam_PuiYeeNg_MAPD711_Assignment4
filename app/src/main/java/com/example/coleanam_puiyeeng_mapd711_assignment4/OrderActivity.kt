package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPep.setOnClickListener{


            startActivity(Intent(this, InfoActivity::class.java))
        }

        binding.buttonDeluxe.setOnClickListener{


            startActivity(Intent(this, InfoActivity::class.java))
        }

        binding.buttonMeat.setOnClickListener{


            startActivity(Intent(this, InfoActivity::class.java))
        }
    }
}