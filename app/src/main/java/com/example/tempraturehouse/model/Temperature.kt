package com.example.tempraturehouse.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temperature_table")
class Temperature (
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val temperature: String
)