package com.example.tempraturehouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tempraturehouse.adapter.TemperatureAdapter
import com.example.tempraturehouse.databinding.ActivityTemperaturesBinding
import com.example.tempraturehouse.viewModel.MainViewModel

class TemperaturesActivity : AppCompatActivity() {
    private val binding: ActivityTemperaturesBinding by lazy {
        ActivityTemperaturesBinding.inflate(layoutInflater)
    }
    lateinit var temperatureAdapter: TemperatureAdapter
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerTemp.layoutManager = LinearLayoutManager(this)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.getTemperature(applicationContext)?.observe(this){list->

            temperatureAdapter = TemperatureAdapter(list,applicationContext)

            binding.recyclerTemp.adapter = temperatureAdapter
            temperatureAdapter.notifyDataSetChanged()



        }









    }
}