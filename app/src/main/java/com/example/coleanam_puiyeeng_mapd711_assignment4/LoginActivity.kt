package com.example.coleanam_puiyeeng_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()

        val usernameInput : EditText = findViewById(R.id.editTextUsername)
        val usernameText = usernameInput.text
        var passwordInput : EditText = findViewById(R.id.editTextTextPassword)
        val passwordText = passwordInput.text
        var loginButton : Button = findViewById(R.id.buttonLogin)
        var adminButton : Button = findViewById(R.id.buttonAdmin)

        loginButton.setOnClickListener{
            editor.putString("customer_username", usernameText.toString()).apply()
            editor.putString("customer_password", passwordText.toString()).apply()

            if (usernameText.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty", Toast.LENGTH_SHORT).show()
            }
            else if (passwordText.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }

        adminButton.setOnClickListener{
            editor.putString("admin_username", usernameText.toString()).apply()
            editor.putString("admin_password", passwordText.toString()).apply()

            if (usernameInput.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty", Toast.LENGTH_SHORT).show()
            }
            else if (passwordInput.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }
    }
}