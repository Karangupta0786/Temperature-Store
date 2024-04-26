package com.example.tempraturehouse.repository

//import android.content.Context
//import androidx.lifecycle.LiveData
//import com.example.tempraturehouse.dao.TemperatureDao
//import com.example.tempraturehouse.database.TempDatabase
//import com.example.tempraturehouse.model.Temperature

//class TempRepository {
//
//    companion object{
//
//        private var instance:TempDatabase? = null
//
//        fun getDatabase(applicationContext:Context):TempDatabase?{
//            instance = TempDatabase.initialiseDatabase(applicationContext)
//            return instance
//        }
//
//        fun insert(temperature: Temperature,applicationContext: Context){
//            instance = getDatabase(applicationContext)
//            instance?.dao()?.insert(temperature)
//        }
//
//        fun getTemp(applicationContext: Context):LiveData<List<Temperature>>?{
//            instance = getDatabase(applicationContext)
//            return instance?.dao()?.getTemp()
//        }
//
//
//    }
//
//
//
//}