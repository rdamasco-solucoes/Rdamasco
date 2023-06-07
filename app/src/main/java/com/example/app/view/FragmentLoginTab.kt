package com.example.app.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app.databinding.FragmentLoginTabBinding
import com.google.firebase.auth.FirebaseAuth

class FragmentLoginTab : Fragment() {

    private lateinit var binding: FragmentLoginTabBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginTabBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.txtForgetPassword.setOnClickListener {
            startActivity(Intent(activity, ForgetPassword::class.java))
        }

//      Dando funcionalidade ao botão de login, com tratamento de campos vazios
        binding.btnLogin.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val senha = binding.loginSenha.text.toString()

            when {
                email.isEmpty() -> Toast.makeText(activity, "Digite seu E-mail!", Toast.LENGTH_SHORT).show()
                senha.isEmpty() -> Toast.makeText(activity, "Digite sua senha!", Toast.LENGTH_SHORT).show()
                senha.length <= 3 -> Toast.makeText(activity, "A senha precisa ter pelo menos 4 caracteres!", Toast.LENGTH_SHORT).show()
                else -> firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(activity, ProductsActivity::class.java))
                    }else{
                        Toast.makeText(activity, "Email ou Senha incorretos!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        return binding.root
    }

}