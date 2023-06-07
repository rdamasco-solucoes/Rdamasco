package com.example.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.app.R
import com.example.app.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.forgetPasswordToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24_black)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        firebaseAuth = FirebaseAuth.getInstance()


        binding.btnVerify.setOnClickListener {
            val emailVerify = binding.emailVerify.text.toString()
            firebaseAuth.sendPasswordResetEmail(emailVerify).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Email enviado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Email não cadastrado!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}