package com.example.tempraturehouse.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tempraturehouse.MainActivity
import com.example.tempraturehouse.R
import com.example.tempraturehouse.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy{
        ActivitySignupBinding.inflate(layoutInflater)
    }
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
        }

        if (auth.currentUser != null){
            startActivity(Intent(this@SignupActivity,MainActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.etSignupEmail.text.toString()
            val password = binding.etSignupPassword.text.toString()
            val rePassword = binding.etSignupReEnterPassword.text.toString()

            if (email.isNullOrEmpty() || password.isNullOrEmpty() || rePassword.isNullOrEmpty()){
                Toast.makeText(applicationContext, "Please fill all the blanks", Toast.LENGTH_SHORT).show()
            }
            else{
                if (password != rePassword){
                    Toast.makeText(applicationContext, "Passwords are not same!!", Toast.LENGTH_SHORT).show()
                }
                else{
                    auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task->
                            if (task.isSuccessful){
                                Toast.makeText(applicationContext, "your id has been created", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
                            }
                        }
                }
            }
        }

    }
}