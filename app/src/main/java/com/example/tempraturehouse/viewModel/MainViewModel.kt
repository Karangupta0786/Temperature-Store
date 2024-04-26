package com.example.tempraturehouse.viewModel

//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.tempraturehouse.model.Temperature
//import com.example.tempraturehouse.repository.TempRepository
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class MainViewModel: ViewModel() {
//
//    fun insert(temperature: Temperature,context: Context){
//        viewModelScope.launch {
//            withContext(Dispatchers.IO){
//                TempRepository.insert(temperature, context)
//            }
//        }
//    }
//    fun getTemperature(context: Context):LiveData<List<Temperature>>?{
//        return TempRepository.getTemp(context)
//    }
//}