package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)

        val testID = findViewById<TextView>(R.id.testID)
        val testName = findViewById<TextView>(R.id.testName)

        testID.text = sharedPreferences.getString("customerId", "")
        testName.text = sharedPreferences.getString("customer_username", "")

    }
}