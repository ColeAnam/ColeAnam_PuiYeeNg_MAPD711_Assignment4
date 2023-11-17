package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coleanam_puiyeeng_mapd711_assignment4.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        val username = binding.editTextUsername.text.toString()
        var password = binding.editTextTextPassword.text.toString()

        // Login button click
        binding.buttonLogin.setOnClickListener{
            editor.putString("customer_username", username).apply()
            editor.putString("customer_password", password).apply()

            if (binding.editTextUsername.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty $username", Toast.LENGTH_SHORT).show()
            }
            else if (binding.editTextTextPassword.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }

        // Admin button click
        binding.buttonAdmin.setOnClickListener{
            editor.putString("admin_username", username).apply()
            editor.putString("admin_password", password).apply()

            if (binding.editTextUsername.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty", Toast.LENGTH_SHORT).show()
            }
            else if (binding.editTextTextPassword.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, OrderStatusActivity::class.java))
            }
        }
    }
}