package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AppointmentsListScreen
import com.example.ui.BellaAiScreen
import com.example.ui.BookingScreen
import com.example.ui.HomeScreen
import com.example.ui.SalonViewModel
import com.example.ui.SalonViewModelFactory
import com.example.ui.ServicesScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen() {
    val viewModel: SalonViewModel = viewModel(
        factory = SalonViewModelFactory(
            application = androidx.compose.ui.platform.LocalContext.current.applicationContext as android.app.Application
        )
    )

    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val appointments by viewModel.appointments.collectAsStateWithLifecycle()

    // Active Booking states
    val selectedService by viewModel.selectedService.collectAsStateWithLifecycle()
    val selectedProfessional by viewModel.selectedProfessional.collectAsStateWithLifecycle()
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val selectedTime by viewModel.selectedTime.collectAsStateWithLifecycle()
    val clientName by viewModel.clientName.collectAsStateWithLifecycle()
    val clientPhone by viewModel.clientPhone.collectAsStateWithLifecycle()
    val bookingError by viewModel.bookingError.collectAsStateWithLifecycle()

    // AI consultant states
    val hairType by viewModel.hairType.collectAsStateWithLifecycle()
    val skinType by viewModel.skinType.collectAsStateWithLifecycle()
    val aiQuery by viewModel.aiQuery.collectAsStateWithLifecycle()
    val aiResponse by viewModel.aiResponse.collectAsStateWithLifecycle()
    val isAiLoading by viewModel.isAiLoading.collectAsStateWithLifecycle()

    // Admin states
    val isAdminMode by viewModel.isAdminMode.collectAsStateWithLifecycle()
    val selectedAdminTab by viewModel.selectedAdminTab.collectAsStateWithLifecycle()
    val adminAiQuery by viewModel.adminAiQuery.collectAsStateWithLifecycle()
    val adminAiResponse by viewModel.adminAiResponse.collectAsStateWithLifecycle()
    val isAdminAiLoading by viewModel.isAdminAiLoading.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (isAdminMode) "Painel BellaVita" else "Salão BellaVita",
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        letterSpacing = 0.5.sp,
                        color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = if (isAdminMode) Icons.Default.AdminPanelSettings else Icons.Default.AutoAwesome,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    Row(
                        modifier = Modifier.padding(end = 12.dp),
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isAdminMode) "Equipe" else "Cliente",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        IconButton(
                            onClick = { viewModel.setAdminMode(!isAdminMode) },
                            modifier = Modifier.testTag("admin_toggle_button")
                        ) {
                            Icon(
                                imageVector = if (isAdminMode) Icons.Default.Face else Icons.Default.SupervisorAccount,
                                contentDescription = "Alternar Modo",
                                tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            if (isAdminMode) {
                NavigationBar(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    NavigationBarItem(
                        selected = selectedAdminTab == 0,
                        onClick = { viewModel.selectAdminTab(0) },
                        icon = { Icon(Icons.Default.EventNote, contentDescription = "Agenda") },
                        label = { Text("Agenda", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_admin_agenda")
                    )
                    NavigationBarItem(
                        selected = selectedAdminTab == 1,
                        onClick = { viewModel.selectAdminTab(1) },
                        icon = { Icon(Icons.Default.BarChart, contentDescription = "Estatísticas") },
                        label = { Text("Métricas", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_admin_metrics")
                    )
                    NavigationBarItem(
                        selected = selectedAdminTab == 2,
                        onClick = { viewModel.selectAdminTab(2) },
                        icon = { Icon(Icons.Default.People, contentDescription = "Especialistas") },
                        label = { Text("Equipe", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_admin_staff")
                    )
                    NavigationBarItem(
                        selected = selectedAdminTab == 3,
                        onClick = { viewModel.selectAdminTab(3) },
                        icon = { Icon(Icons.Default.SupportAgent, contentDescription = "Assistente") },
                        label = { Text("Assistente", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_admin_ai")
                    )
                }
            } else {
                NavigationBar(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    // Tab 0: Home
                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { viewModel.selectTab(0) },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Início") },
                        label = { Text("Início", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_home")
                    )

                    // Tab 1: Serviços
                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { viewModel.selectTab(1) },
                        icon = { Icon(Icons.Default.ContentCut, contentDescription = "Serviços") },
                        label = { Text("Serviços", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_services")
                    )

                    // Tab 2: Agendar (Form)
                    NavigationBarItem(
                        selected = selectedTab == 2,
                        onClick = { viewModel.selectTab(2) },
                        icon = { Icon(Icons.Default.Schedule, contentDescription = "Agendar") },
                        label = { Text("Agendar", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_booking")
                    )

                    // Tab 3: Meus Horários (List)
                    NavigationBarItem(
                        selected = selectedTab == 3,
                        onClick = { viewModel.selectTab(3) },
                        icon = { Icon(Icons.Default.CalendarMonth, contentDescription = "Horários") },
                        label = { Text("Meus Horários", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_appointments")
                    )

                    // Tab 4: Bella AI
                    NavigationBarItem(
                        selected = selectedTab == 4,
                        onClick = { viewModel.selectTab(4) },
                        icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "Bella AI") },
                        label = { Text("Bella AI", fontSize = 11.sp) },
                        modifier = Modifier.testTag("nav_tab_ai")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (isAdminMode) {
                com.example.ui.AdminDashboardScreen(
                    selectedAdminTab = selectedAdminTab,
                    appointments = appointments,
                    adminAiQuery = adminAiQuery,
                    adminAiResponse = adminAiResponse,
                    isAdminAiLoading = isAdminAiLoading,
                    onUpdateAdminAiQuery = { viewModel.updateAdminAiQuery(it) },
                    onGetAdminAssistance = { viewModel.getAdminAssistance(it) },
                    onDeleteAppointment = { viewModel.cancelAppointment(it) },
                    onSaveAppointment = { viewModel.saveAppointment(it) },
                    onNavigateToClientMode = { viewModel.setAdminMode(false) }
                )
            } else {
                when (selectedTab) {
                    0 -> HomeScreen(
                        onNavigateToServices = { viewModel.selectTab(1) },
                        onNavigateToAI = { viewModel.selectTab(4) },
                        onNavigateToBookings = { viewModel.selectTab(3) },
                        onNavigateToAdmin = { viewModel.setAdminMode(true) }
                    )
                    1 -> ServicesScreen(
                        onServiceSelectedForBooking = { service ->
                            viewModel.selectService(service)
                            viewModel.selectTab(2) // Jump to scheduling form with the service pre-selected!
                        }
                    )
                    2 -> BookingScreen(
                        selectedService = selectedService,
                        selectedProfessional = selectedProfessional,
                        selectedDate = selectedDate,
                        selectedTime = selectedTime,
                        clientName = clientName,
                        clientPhone = clientPhone,
                        bookingError = bookingError,
                        onSelectService = { viewModel.selectService(it) },
                        onSelectProfessional = { viewModel.selectProfessional(it) },
                        onSelectDate = { viewModel.selectDate(it) },
                        onSelectTime = { viewModel.selectTime(it) },
                        onUpdateName = { viewModel.updateClientName(it) },
                        onUpdatePhone = { viewModel.updateClientPhone(it) },
                        onConfirmBooking = { viewModel.bookAppointment() }
                    )
                    3 -> AppointmentsListScreen(
                        appointments = appointments,
                        onCancelClick = { appointmentId ->
                            viewModel.cancelAppointment(appointmentId)
                        },
                        onNavigateToServices = { viewModel.selectTab(1) }
                    )
                    4 -> BellaAiScreen(
                        hairType = hairType,
                        skinType = skinType,
                        aiQuery = aiQuery,
                        aiResponse = aiResponse,
                        isLoading = isAiLoading,
                        onHairTypeChanged = { viewModel.updateHairType(it) },
                        onSkinTypeChanged = { viewModel.updateSkinType(it) },
                        onQueryChanged = { viewModel.updateAiQuery(it) },
                        onQuerySubmitted = { viewModel.getBeautyRecommendation() }
                    )
                }
            }
        }
    }
}
