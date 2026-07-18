package com.example.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.Appointment
import com.example.data.AppointmentRepository
import com.example.data.GeminiService
import com.example.data.Professional
import com.example.data.Service
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SalonViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppointmentRepository = AppointmentRepository(
        AppDatabase.getDatabase(application).appointmentDao()
    )

    // Tab Navigation: 0 = Home, 1 = Serviços, 2 = Agendamentos, 3 = Bella AI
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    fun selectTab(tab: Int) {
        _selectedTab.value = tab
    }

    // Appointments observed from database
    val appointments: StateFlow<List<Appointment>> = repository.allAppointments
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Booking State
    private val _selectedService = MutableStateFlow<Service?>(null)
    val selectedService = _selectedService.asStateFlow()

    private val _selectedProfessional = MutableStateFlow<Professional?>(null)
    val selectedProfessional = _selectedProfessional.asStateFlow()

    private val _selectedDate = MutableStateFlow("")
    val selectedDate = _selectedDate.asStateFlow()

    private val _selectedTime = MutableStateFlow("")
    val selectedTime = _selectedTime.asStateFlow()

    private val _clientName = MutableStateFlow("")
    val clientName = _clientName.asStateFlow()

    private val _clientPhone = MutableStateFlow("")
    val clientPhone = _clientPhone.asStateFlow()

    private val _bookingError = MutableStateFlow<String?>(null)
    val bookingError = _bookingError.asStateFlow()

    init {
        // Initialize with tomorrow's date by default
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        _selectedDate.value = dateFormat.format(calendar.time)

        // Load saved client details for convenience
        val sharedPrefs = application.getSharedPreferences("bellavita_prefs", Context.MODE_PRIVATE)
        _clientName.value = sharedPrefs.getString("client_name", "") ?: ""
        _clientPhone.value = sharedPrefs.getString("client_phone", "") ?: ""

        // Pre-populate database with a sample appointment if empty
        viewModelScope.launch {
            try {
                if (repository.getAppointmentsCount() == 0) {
                    val tomorrowVal = Calendar.getInstance()
                    tomorrowVal.add(Calendar.DAY_OF_YEAR, 1)
                    val tomorrowStr = dateFormat.format(tomorrowVal.time)

                    repository.insert(
                        Appointment(
                            serviceName = "Corte & Escova",
                            servicePrice = 120.00,
                            professionalName = "Gabriela Silva",
                            date = tomorrowStr,
                            time = "14:30",
                            clientName = "Bella Alencar",
                            clientPhone = "(11) 98765-4321"
                        )
                    )
                }
            } catch (e: Exception) {
                // Ignore or log error
            }
        }
    }

    fun selectService(service: Service?) {
        _selectedService.value = service
        _bookingError.value = null
    }

    fun selectProfessional(professional: Professional?) {
        _selectedProfessional.value = professional
        _selectedTime.value = "" // Reset time when professional changes
        _bookingError.value = null
    }

    fun selectDate(date: String) {
        _selectedDate.value = date
        _bookingError.value = null
    }

    fun selectTime(time: String) {
        _selectedTime.value = time
        _bookingError.value = null
    }

    fun updateClientName(name: String) {
        _clientName.value = name
        _bookingError.value = null
    }

    fun updateClientPhone(phone: String) {
        _clientPhone.value = phone
        _bookingError.value = null
    }

    fun bookAppointment(): Boolean {
        val service = _selectedService.value
        val professional = _selectedProfessional.value
        val date = _selectedDate.value
        val time = _selectedTime.value
        val name = _clientName.value
        val phone = _clientPhone.value

        if (service == null) {
            _bookingError.value = "Por favor, selecione um serviço."
            return false
        }
        if (professional == null) {
            _bookingError.value = "Por favor, selecione um profissional."
            return false
        }
        if (date.isBlank()) {
            _bookingError.value = "Por favor, insira uma data."
            return false
        }
        if (time.isBlank()) {
            _bookingError.value = "Por favor, selecione um horário."
            return false
        }
        if (name.isBlank()) {
            _bookingError.value = "Por favor, insira seu nome."
            return false
        }
        if (phone.isBlank()) {
            _bookingError.value = "Por favor, insira seu telefone."
            return false
        }

        viewModelScope.launch {
            repository.insert(
                Appointment(
                    serviceName = service.name,
                    servicePrice = service.price,
                    professionalName = professional.name,
                    date = date,
                    time = time,
                    clientName = name,
                    clientPhone = phone
                )
            )
            // Save client details for convenience
            val sharedPrefs = getApplication<Application>().getSharedPreferences("bellavita_prefs", Context.MODE_PRIVATE)
            sharedPrefs.edit()
                .putString("client_name", name)
                .putString("client_phone", phone)
                .apply()

            // Reset fields
            _selectedService.value = null
            _selectedProfessional.value = null
            _selectedTime.value = ""
            _bookingError.value = null
            // Switch to Bookings Tab
            _selectedTab.value = 3
        }
        return true
    }

    fun cancelAppointment(id: Int) {
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }

    // AI Consultant State
    private val _hairType = MutableStateFlow("Liso")
    val hairType = _hairType.asStateFlow()

    private val _skinType = MutableStateFlow("Normal")
    val skinType = _skinType.asStateFlow()

    private val _aiQuery = MutableStateFlow("")
    val aiQuery = _aiQuery.asStateFlow()

    private val _aiResponse = MutableStateFlow("")
    val aiResponse = _aiResponse.asStateFlow()

    private val _isAiLoading = MutableStateFlow(false)
    val isAiLoading = _isAiLoading.asStateFlow()

    fun updateHairType(type: String) {
        _hairType.value = type
    }

    fun updateSkinType(type: String) {
        _skinType.value = type
    }

    fun updateAiQuery(query: String) {
        _aiQuery.value = query
    }

    fun getBeautyRecommendation() {
        val query = _aiQuery.value
        if (query.isBlank()) return

        viewModelScope.launch {
            _isAiLoading.value = true
            _aiResponse.value = ""
            try {
                val result = GeminiService.getBeautyRecommendation(
                    hairType = _hairType.value,
                    skinType = _skinType.value,
                    query = query
                )
                _aiResponse.value = result
            } catch (e: Exception) {
                _aiResponse.value = "Desculpe, ocorreu um erro ao gerar suas recomendações: ${e.localizedMessage}"
            } finally {
                _isAiLoading.value = false
            }
        }
    }

    // --- Admin Dashboard State & Actions ---
    private val _isAdminMode = MutableStateFlow(false)
    val isAdminMode = _isAdminMode.asStateFlow()

    private val _selectedAdminTab = MutableStateFlow(0)
    val selectedAdminTab = _selectedAdminTab.asStateFlow()

    private val _adminAiQuery = MutableStateFlow("")
    val adminAiQuery = _adminAiQuery.asStateFlow()

    private val _adminAiResponse = MutableStateFlow("")
    val adminAiResponse = _adminAiResponse.asStateFlow()

    private val _isAdminAiLoading = MutableStateFlow(false)
    val isAdminAiLoading = _isAdminAiLoading.asStateFlow()

    fun setAdminMode(enabled: Boolean) {
        _isAdminMode.value = enabled
    }

    fun selectAdminTab(tab: Int) {
        _selectedAdminTab.value = tab
    }

    fun updateAdminAiQuery(query: String) {
        _adminAiQuery.value = query
    }

    fun saveAppointment(appointment: Appointment) {
        viewModelScope.launch {
            repository.insert(appointment)
        }
    }

    fun getAdminAssistance(contextInfo: String) {
        val query = _adminAiQuery.value
        if (query.isBlank()) return

        viewModelScope.launch {
            _isAdminAiLoading.value = true
            _adminAiResponse.value = ""
            try {
                val response = GeminiService.getAdminAssistance(contextInfo, query)
                _adminAiResponse.value = response
            } catch (e: Exception) {
                _adminAiResponse.value = "Erro ao carregar assistência administrativa: ${e.localizedMessage}"
            } finally {
                _isAdminAiLoading.value = false
            }
        }
    }
}

class SalonViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SalonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SalonViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
