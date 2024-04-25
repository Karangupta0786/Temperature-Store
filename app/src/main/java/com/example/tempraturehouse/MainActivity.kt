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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel?.insert(temperature,this@MainActivity)
            }
                binding.etTemperature.setText("")
             Toast.makeText(applicationContext, "Temperature saved successfully!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}