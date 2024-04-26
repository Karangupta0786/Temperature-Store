package com.example.tempraturehouse.database

//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.tempraturehouse.dao.TemperatureDao
//import com.example.tempraturehouse.model.Temperature

//@Database(entities = [Temperature::class], version = 1)
//abstract class TempDatabase:RoomDatabase() {

//    abstract fun dao():TemperatureDao

//    companion object{
//        @Volatile
//        var instance:TempDatabase? = null
//
//        fun initialiseDatabase(applicationContext: Context):TempDatabase?{
//            if (instance == null){
//                instance = Room.databaseBuilder(
//                    applicationContext,
//                    TempDatabase::class.java,
//                    "database_name"
//                )
//                    .build()
//            }
//                return instance!!
//        }
//
//    }


//}