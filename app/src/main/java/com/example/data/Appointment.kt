package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val serviceName: String,
    val servicePrice: Double,
    val professionalName: String,
    val date: String,
    val time: String,
    val clientName: String,
    val clientPhone: String,
    val timestamp: Long = System.currentTimeMillis()
)
