package com.example.data

import kotlinx.coroutines.flow.Flow

class AppointmentRepository(private val appointmentDao: AppointmentDao) {
    val allAppointments: Flow<List<Appointment>> = appointmentDao.getAllAppointments()

    suspend fun insert(appointment: Appointment) {
        appointmentDao.insertAppointment(appointment)
    }

    suspend fun deleteById(id: Int) {
        appointmentDao.deleteAppointmentById(id)
    }

    suspend fun getAppointmentsCount(): Int {
        return appointmentDao.getAppointmentsCount()
    }
}
