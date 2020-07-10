package com.aprp.saltstudioandroid.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.aprp.saltstudioandroid.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Admin Use Only !",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (!it.isSuccessful){
                        startActivity(Intent(this, LoginActivity::class.java))
                        return@addOnCompleteListener
                    } else
                        Toast.makeText(this,"Selamat Datang !", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, AdminMainActivity::class.java))
                }
                .addOnFailureListener {
                    Log.d("Main","Failed Login: ${it.message}")
                    Toast.makeText(this,"Admin Area !", Toast.LENGTH_SHORT).show()
                }
        }
    }
}