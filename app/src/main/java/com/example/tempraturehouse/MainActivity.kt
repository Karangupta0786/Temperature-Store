package com.example.tempraturehouse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.tempraturehouse.databinding.ActivityMainBinding
import com.example.tempraturehouse.model.Temperature
import com.example.tempraturehouse.viewModel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var mainViewModel: MainViewModel
    lateinit var databaseReference: DatabaseReference
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        databaseReference = Firebase.database.reference

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this@MainActivity,TemperaturesActivity::class.java))
        }

        binding.etTemperature.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.isNullOrEmpty()){
                    binding.btnSave.setCardBackgroundColor(ContextCompat.getColor(applicationContext,R.color.disable))
                }
                else{
                    binding.btnSave.setCardBackgroundColor(ContextCompat.getColor(applicationContext,R.color.clickable))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


        binding.btnSave.setOnClickListener {
            if (binding.etTemperature.text.isNullOrEmpty()){
                Toast.makeText(applicationContext, "Please enter the temperature!!", Toast.LENGTH_SHORT).show()
            }
            else{
            var temp = binding.etTemperature.text.toString()
            var temperature = Temperature(0,temp)

            Log.e("temp is:===",temp)
//            CoroutineScope(Dispatchers.IO).launch {
//                mainViewModel?.insert(temperature,this@MainActivity)
//            }

                val currentUser = auth.currentUser

                currentUser?.let { user->
                    val tempKey = databaseReference.child("users").child(user.uid).child("temp").push().key



                    if (tempKey != null){
                        databaseReference.child("users").child(user.uid).child("temp").child(tempKey).setValue(temperature)
                            .addOnCompleteListener { task->
                                if (task.isSuccessful){
                                    Toast.makeText(applicationContext, "Temperature saved successfully!!", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                else{
                                    Toast.makeText(applicationContext, "Something went wrong!!", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                    }

                }

                binding.etTemperature.setText("")
             Toast.makeText(applicationContext, "Temperature saved successfully!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}