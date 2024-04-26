package com.example.tempraturehouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tempraturehouse.adapter.TemperatureAdapter
import com.example.tempraturehouse.databinding.ActivityTemperaturesBinding
import com.example.tempraturehouse.model.Temperature
//import com.example.tempraturehouse.viewModel.MainViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class TemperaturesActivity : AppCompatActivity() {
    private val binding: ActivityTemperaturesBinding by lazy {
        ActivityTemperaturesBinding.inflate(layoutInflater)
    }
    lateinit var temperatureAdapter: TemperatureAdapter
//    lateinit var mainViewModel: MainViewModel
    lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        databaseReference = Firebase.database.reference

        val currentUser = auth.currentUser
        binding.recyclerTemp.layoutManager = LinearLayoutManager(this)

        currentUser?.let {user->
            val tempReference = databaseReference.child("users").child(user.uid).child("temp")

            tempReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tempList = mutableListOf<Temperature>()

                    for (tempSnap in snapshot.children){
                        val temp = tempSnap.getValue(Temperature::class.java)

                        temp?.let {
                            tempList.add(temp)
                        }

                    }


                    temperatureAdapter = TemperatureAdapter(tempList,applicationContext)

                    binding.recyclerTemp.adapter = temperatureAdapter
                    temperatureAdapter.notifyDataSetChanged()

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }



//        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

//        mainViewModel.getTemperature(applicationContext)?.observe(this){list->
//
//
//
//
//        }









    }
}