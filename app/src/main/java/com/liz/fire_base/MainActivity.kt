package com.liz.fire_base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var EdtEmail: EditText
    lateinit var EdtPassword: EditText
    lateinit var EdtconfPassword: EditText
    lateinit var BtnSignup: Button
    lateinit var TvRedirectLogin: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EdtEmail = findViewById(R.id.emailaddress)
        EdtPassword = findViewById(R.id.password)
        EdtconfPassword = findViewById(R.id.confirmpassword)
        BtnSignup = findViewById(R.id.signup)
        TvRedirectLogin = findViewById(R.id.login)
        auth = Firebase.auth

        BtnSignup.setOnClickListener {
            SignUpUser()
        }
        TvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun SignUpUser() {
        val email=EdtEmail.text.toString()
        val pass=EdtPassword.text.toString()
        val confirmpass=EdtconfPassword.text.toString()

        if (email.isBlank() || pass.isBlank() || confirmpass.isBlank()) {
            Toast.makeText(this, "Email and Password can't blank", Toast.LENGTH_LONG).show()
            return
        } else if (pass != confirmpass) {
            Toast.makeText(this, "Passswords do not match", Toast.LENGTH_LONG).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "You signed in successfully", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to create", Toast.LENGTH_LONG).show()
            }
        }
    }
}