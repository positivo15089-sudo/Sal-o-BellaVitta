package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Appointment
import com.example.data.Professional
import com.example.data.SalonData
import com.example.data.Service
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun HomeScreen(
    onNavigateToServices: () -> Unit,
    onNavigateToAI: () -> Unit,
    onNavigateToBookings: () -> Unit,
    onNavigateToAdmin: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("home_screen"),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Editorial Header Section
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "MAISON DE BEAUTÉ",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Olá, Bella",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                // Exquisite circle with subtle pink/gradient border
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                                )
                            )
                        )
                        .border(2.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Editorial Hero Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .clickable { onNavigateToServices() }
            ) {
                // Editorial dark/rose translucent scrim
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.05f),
                                    Color.Black.copy(alpha = 0.7f)
                                )
                            )
                        )
                        .padding(24.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "DESTAQUE DA SEMANA",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White.copy(alpha = 0.85f),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )

                        Column {
                            Text(
                                text = "Sessão de Brilho &\nCor Natural",
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.White,
                                lineHeight = 36.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = onNavigateToServices,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFD8E4), // EditorialAccentPill
                                    contentColor = Color(0xFF31101B)    // EditorialBrandDark
                                ),
                                shape = RoundedCornerShape(24.dp),
                                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                            ) {
                                Text(
                                    text = "RESERVAR AGORA",
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Quick Actions Row
        item {
            Column {
                Text(
                    text = "Navegar por Categoria",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionCard(
                        title = "Serviços",
                        icon = Icons.Default.ContentCut,
                        backgroundColor = Color(0xFFFFD8E4),
                        iconColor = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.weight(1f).testTag("action_services"),
                        onClick = onNavigateToServices
                    )
                    QuickActionCard(
                        title = "Bella AI",
                        icon = Icons.Default.AutoAwesome,
                        backgroundColor = Color(0xFFEFD8DE),
                        iconColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f).testTag("action_ai"),
                        onClick = onNavigateToAI
                    )
                    QuickActionCard(
                        title = "Agendados",
                        icon = Icons.Default.CalendarMonth,
                        backgroundColor = Color(0xFFFFD8E4),
                        iconColor = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.weight(1f).testTag("action_bookings"),
                        onClick = onNavigateToBookings
                    )
                }
            }
        }

        // Area Administrativa Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToAdmin() }
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                        RoundedCornerShape(24.dp)
                    )
                    .testTag("action_admin_dashboard"),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AdminPanelSettings,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "Área da Equipe & Gestão",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = "Gerencie a agenda, métricas e troco",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Acessar",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Highlighted Services Section
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Serviços Populares",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    TextButton(onClick = onNavigateToServices) {
                        Text(
                            text = "Ver Todos",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(SalonData.services.take(3)) { service ->
                        PopularServiceCard(service = service, onClick = onNavigateToServices)
                    }
                }
            }
        }

        // Featured Specialists (Direct from Design HTML)
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nossos Especialistas",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Ver todos",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { onNavigateToServices() }
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SalonData.professionals.take(2).forEach { prof ->
                        val emoji = if (prof.name.contains("Clara") || prof.name.contains("Ana")) "👩🏻‍🦱" else "👱🏻‍♀️"
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp)),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                        .border(2.dp, Color(0xFFEFD8DE), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = emoji, fontSize = 28.sp)
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = prof.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = prof.specialty.uppercase(),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }

        // About the Salon Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Maison BellaVita",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Oferecemos uma experiência única de relaxamento e estética. Nossa equipe é composta por profissionais altamente qualificados e atualizados com as últimas tendências internacionais. Utilizamos apenas produtos premium e tecnologia avançada para garantir o melhor cuidado para seu cabelo, pele e unhas.",
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun ServicesScreen(
    onServiceSelectedForBooking: (Service) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("Todos") }
    val categories = listOf("Todos", "Cabelo", "Unhas", "Estética", "Massagem")
    val categoryEmojis = mapOf(
        "Todos" to "✨",
        "Cabelo" to "✂️",
        "Unhas" to "💅",
        "Estética" to "🎨",
        "Massagem" to "💆‍♀️"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("services_screen")
    ) {
        // Section title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 8.dp)
        ) {
            Text(
                text = "Nossos Serviços",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Selecione uma categoria para explorar os tratamentos.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }

        // Category Filter Row
        ScrollableTabRow(
            selectedTabIndex = categories.indexOf(selectedCategory),
            edgePadding = 20.dp,
            containerColor = Color.Transparent,
            divider = {},
            indicator = {}
        ) {
            categories.forEachIndexed { index, category ->
                val selected = category == selectedCategory
                val emoji = categoryEmojis[category] ?: "✨"
                Tab(
                    selected = selected,
                    onClick = { selectedCategory = category },
                    modifier = Modifier.padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (selected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.surface
                            )
                            .border(
                                width = 1.dp,
                                color = if (selected) Color.Transparent else Color(0xFFEFD8DE),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(text = emoji, fontSize = 14.sp)
                            Text(
                                text = category,
                                color = if (selected) Color.White else MaterialTheme.colorScheme.onBackground,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }

        val filteredServices = if (selectedCategory == "Todos") {
            SalonData.services
        } else {
            SalonData.services.filter { it.category == selectedCategory }
        }

        LazyColumn(
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(filteredServices) { service ->
                ServiceCard(
                    service = service,
                    onBookClick = { onServiceSelectedForBooking(service) }
                )
            }
        }
    }
}

@Composable
fun BookingScreen(
    selectedService: Service?,
    selectedProfessional: Professional?,
    selectedDate: String,
    selectedTime: String,
    clientName: String,
    clientPhone: String,
    bookingError: String?,
    onSelectService: (Service?) -> Unit,
    onSelectProfessional: (Professional?) -> Unit,
    onSelectDate: (String) -> Unit,
    onSelectTime: (String) -> Unit,
    onUpdateName: (String) -> Unit,
    onUpdatePhone: (String) -> Unit,
    onConfirmBooking: () -> Unit
) {
    var expandedServices by remember { mutableStateOf(false) }
    var expandedProfessionals by remember { mutableStateOf(false) }

    // Generates a list of upcoming dates for easy selection
    val upcomingDates = remember {
        val list = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())
        val dayNumFormat = SimpleDateFormat("dd", Locale.getDefault())

        for (i in 1..7) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            list.add(dateFormat.format(calendar.time))
        }
        list
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("booking_screen"),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Screen Title
        item {
            Text(
                text = "Agende seu Horário",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Escolha o serviço, profissional e horário ideal.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }

        // Section 1: Choose Service
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "1. Selecione o Serviço",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedCard(
                        onClick = { expandedServices = !expandedServices },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(20.dp))
                            .testTag("service_dropdown"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                if (selectedService != null) {
                                    Text(
                                        text = selectedService.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                    Text(
                                        text = "R$ %.2f • %d min".format(selectedService.price, selectedService.durationMinutes),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                } else {
                                    Text(
                                        text = "Toque para escolher um serviço",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                    )
                                }
                            }
                            Icon(
                                imageVector = if (expandedServices) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                contentDescription = "Expandir",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expandedServices,
                        onDismissRequest = { expandedServices = false },
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
                        SalonData.services.forEach { service ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(service.name, fontWeight = FontWeight.Bold)
                                        Text("R$ %.2f • %d min".format(service.price, service.durationMinutes), color = MaterialTheme.colorScheme.primary)
                                    }
                                },
                                onClick = {
                                    onSelectService(service)
                                    expandedServices = false
                                }
                            )
                        }
                    }
                }
            }
        }

        // Section 2: Choose Professional
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "2. Escolha o Profissional",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedCard(
                        onClick = { expandedProfessionals = !expandedProfessionals },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(20.dp))
                            .testTag("professional_dropdown"),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                if (selectedProfessional != null) {
                                    Text(
                                        text = selectedProfessional.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                    Text(
                                        text = selectedProfessional.specialty,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                } else {
                                    Text(
                                        text = "Toque para escolher um profissional",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                    )
                                }
                            }
                            Icon(
                                imageVector = if (expandedProfessionals) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                contentDescription = "Expandir",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expandedProfessionals,
                        onDismissRequest = { expandedProfessionals = false },
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
                        SalonData.professionals.forEach { prof ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(prof.name, fontWeight = FontWeight.Bold)
                                        Text(prof.specialty, style = MaterialTheme.typography.bodySmall)
                                    }
                                },
                                onClick = {
                                    onSelectProfessional(prof)
                                    expandedProfessionals = false
                                }
                            )
                        }
                    }
                }
            }
        }

        // Section 3: Date Picker
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "3. Selecione a Data",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(upcomingDates) { date ->
                        val selected = date == selectedDate
                        val calendar = Calendar.getInstance()
                        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val parsed = format.parse(date)
                        if (parsed != null) {
                            calendar.time = parsed
                        }
                        val dayOfWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time)
                            .replace(".", "").uppercase(Locale.getDefault())
                        val dayOfMonth = SimpleDateFormat("dd", Locale.getDefault()).format(calendar.time)

                        Box(
                            modifier = Modifier
                                .width(65.dp)
                                .height(80.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    if (selected) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.surface
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (selected) Color.Transparent else Color(0xFFEFD8DE),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .clickable { onSelectDate(date) }
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = dayOfWeek,
                                    color = if (selected) Color.White.copy(alpha = 0.85f) else MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = dayOfMonth,
                                    color = if (selected) Color.White else MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Section 4: Time Slot Picker
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "4. Escolha o Horário",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                if (selectedProfessional != null) {
                    val chunks = selectedProfessional.availableHours.chunked(3)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        chunks.forEach { rowHours ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                rowHours.forEach { hour ->
                                    val selected = hour == selectedTime
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(
                                                if (selected) MaterialTheme.colorScheme.primary
                                                else MaterialTheme.colorScheme.surface
                                            )
                                            .border(
                                                width = 1.dp,
                                                color = if (selected) Color.Transparent else Color(0xFFEFD8DE),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .clickable { onSelectTime(hour) }
                                            .padding(vertical = 12.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = hour,
                                            color = if (selected) Color.White else MaterialTheme.colorScheme.onBackground,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                                if (rowHours.size < 3) {
                                    repeat(3 - rowHours.size) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(20.dp)),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Text(
                            text = "Selecione um profissional para carregar os horários disponíveis.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }

        // Section 5: Client Form
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "5. Seus Dados",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                OutlinedTextField(
                    value = clientName,
                    onValueChange = onUpdateName,
                    label = { Text("Nome Completo") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    modifier = Modifier.fillMaxWidth().testTag("client_name_input"),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color(0xFFEFD8DE)
                    )
                )

                OutlinedTextField(
                    value = clientPhone,
                    onValueChange = onUpdatePhone,
                    label = { Text("Celular / WhatsApp") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    modifier = Modifier.fillMaxWidth().testTag("client_phone_input"),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color(0xFFEFD8DE)
                    )
                )
            }
        }

        // Error message if validation fails
        if (bookingError != null) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = bookingError,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(14.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Primary Confirm Button
        item {
            Button(
                onClick = onConfirmBooking,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("confirm_booking_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFD8E4), // EditorialAccentPill
                    contentColor = Color(0xFF31101B)    // EditorialBrandDark
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "CONFIRMAR AGENDAMENTO",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun AppointmentsListScreen(
    appointments: List<Appointment>,
    onCancelClick: (Int) -> Unit,
    onNavigateToServices: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("appointments_screen")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
        ) {
            Column {
                Text(
                    text = "Meus Agendamentos",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Consulte e gerencie seus horários reservados.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }

        if (appointments.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                CircleShape
                            )
                            .border(1.dp, Color(0xFFEFD8DE), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Text(
                        text = "Nenhum Agendamento",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        text = "Você ainda não possui nenhum horário reservado no Salão BellaVita. Escolha um serviço e faça sua reserva!",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )

                    Button(
                        onClick = onNavigateToServices,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFD8E4), // EditorialAccentPill
                            contentColor = Color(0xFF31101B)    // EditorialBrandDark
                        ),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = "AGENDAR AGORA",
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(appointments) { appointment ->
                    AppointmentCard(
                        appointment = appointment,
                        onCancelClick = { onCancelClick(appointment.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun BellaAiScreen(
    hairType: String,
    skinType: String,
    aiQuery: String,
    aiResponse: String,
    isLoading: Boolean,
    onHairTypeChanged: (String) -> Unit,
    onSkinTypeChanged: (String) -> Unit,
    onQueryChanged: (String) -> Unit,
    onQuerySubmitted: () -> Unit
) {
    val hairTypes = listOf("Liso", "Ondulado", "Cacheado", "Crespo")
    val skinTypes = listOf("Seca", "Oleosa", "Mista", "Normal")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .testTag("bella_ai_screen"),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFFFD8E4).copy(alpha = 0.4f)) // EditorialAccentPill background
                    .border(
                        1.dp,
                        Color(0xFFEFD8DE),
                        RoundedCornerShape(24.dp)
                    )
                    .padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                Color(0xFFFFD8E4),
                                CircleShape
                            )
                            .border(1.dp, Color(0xFFEFD8DE), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color(0xFF31101B), // EditorialBrandDark
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "Bella AI",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "Sua assistente inteligente de beleza e bem-estar.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }

        // Profile customizer (Hair/Skin)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Customize o Perfil para Dicas Precisas",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    // Hair Selection
                    Column {
                        Text(
                            text = "Tipo de Cabelo:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(hairTypes) { type ->
                                val isSelected = type == hairType
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onHairTypeChanged(type) },
                                    label = { Text(type) }
                                )
                            }
                        }
                    }

                    // Skin Selection
                    Column {
                        Text(
                            text = "Tipo de Pele:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(skinTypes) { type ->
                                val isSelected = type == skinType
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSkinTypeChanged(type) },
                                    label = { Text(type) }
                                )
                            }
                        }
                    }
                }
            }
        }

        // Search/Question input
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "O que você gostaria de perguntar?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                OutlinedTextField(
                    value = aiQuery,
                    onValueChange = onQueryChanged,
                    placeholder = { Text("Ex: Como manter o cabelo hidratado? Qual o melhor tratamento facial para minha pele?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .testTag("ai_query_input"),
                    shape = RoundedCornerShape(20.dp),
                    maxLines = 4,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color(0xFFEFD8DE)
                    )
                )

                Button(
                    onClick = onQuerySubmitted,
                    enabled = aiQuery.isNotBlank() && !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("ai_submit_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD8E4), // EditorialAccentPill
                        contentColor = Color(0xFF31101B)    // EditorialBrandDark
                    ),
                    shape = RoundedCornerShape(26.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color(0xFF31101B),
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(Icons.Default.Send, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "PERGUNTAR À BELLA AI",
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }

        // Response Container
        item {
            AnimatedVisibility(
                visible = aiResponse.isNotBlank() || isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp))
                        .testTag("ai_response_container"),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Conselho da Bella AI",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        if (isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "A Bella está elaborando seu conselho personalizado...",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                                )
                            }
                        } else {
                            Text(
                                text = aiResponse,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 22.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
    }
}

// Sub-components

@Composable
fun QuickActionCard(
    title: String,
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(backgroundColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun PopularServiceCard(
    service: Service,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(150.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val icon = when (service.iconName) {
                    "content_cut" -> Icons.Default.ContentCut
                    "brush" -> Icons.Default.Brush
                    "face" -> Icons.Default.Face
                    "palette" -> Icons.Default.Palette
                    "spa" -> Icons.Default.Spa
                    else -> Icons.Default.Favorite
                }
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }

            Column {
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "R$ %.2f".format(service.price),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun ServiceCard(
    service: Service,
    onBookClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp))
            .testTag("service_card_${service.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f), CircleShape)
                            .border(1.dp, Color(0xFFEFD8DE), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        val icon = when (service.iconName) {
                            "content_cut" -> Icons.Default.ContentCut
                            "brush" -> Icons.Default.Brush
                            "face" -> Icons.Default.Face
                            "palette" -> Icons.Default.Palette
                            "spa" -> Icons.Default.Spa
                            else -> Icons.Default.Favorite
                        }
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Column {
                        Text(
                            text = service.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "R$ %.2f".format(service.price),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "•",
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                            )
                            Text(
                                text = "${service.durationMinutes} min",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = service.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBookClick,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFD8E4), // EditorialAccentPill
                    contentColor = Color(0xFF31101B)    // EditorialBrandDark
                ),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Icon(Icons.Default.CalendarToday, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "AGENDAR",
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun AppointmentCard(
    appointment: Appointment,
    onCancelClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp))
            .testTag("appointment_card_${appointment.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Header: Status indicator + Cancel Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFD8E4)) // EditorialAccentPill
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Confirmado",
                        color = Color(0xFF31101B), // EditorialBrandDark
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                IconButton(
                    onClick = onCancelClick,
                    modifier = Modifier.testTag("cancel_appointment_btn_${appointment.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Cancelar Horário",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Service details
            Text(
                text = appointment.serviceName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Professional, Date, and Time detail rows with icons
            DetailRow(
                icon = Icons.Outlined.Person,
                label = "Profissional",
                value = appointment.professionalName
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(
                icon = Icons.Outlined.CalendarMonth,
                label = "Data",
                value = appointment.date
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(
                icon = Icons.Outlined.AccessTime,
                label = "Horário",
                value = appointment.time
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(
                icon = Icons.Outlined.AttachMoney,
                label = "Valor",
                value = "R$ %.2f".format(appointment.servicePrice)
            )

            Divider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = Color(0xFFEFD8DE)
            )

            // Client info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "CLIENTE",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = appointment.clientName,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "CONTATO",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = appointment.clientPhone,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun DetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun AdminDashboardScreen(
    selectedAdminTab: Int,
    appointments: List<Appointment>,
    adminAiQuery: String,
    adminAiResponse: String,
    isAdminAiLoading: Boolean,
    onUpdateAdminAiQuery: (String) -> Unit,
    onGetAdminAssistance: (String) -> Unit,
    onDeleteAppointment: (Int) -> Unit,
    onSaveAppointment: (Appointment) -> Unit,
    onNavigateToClientMode: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (selectedAdminTab) {
            0 -> AdminAgendaTab(
                appointments = appointments,
                onDeleteAppointment = onDeleteAppointment,
                onSaveAppointment = onSaveAppointment
            )
            1 -> AdminMetricsTab(
                appointments = appointments
            )
            2 -> AdminStaffTab()
            3 -> AdminAiTab(
                appointments = appointments,
                adminAiQuery = adminAiQuery,
                adminAiResponse = adminAiResponse,
                isAdminAiLoading = isAdminAiLoading,
                onUpdateAdminAiQuery = onUpdateAdminAiQuery,
                onGetAdminAssistance = onGetAdminAssistance
            )
        }
    }
}

@Composable
fun AdminAgendaTab(
    appointments: List<Appointment>,
    onDeleteAppointment: (Int) -> Unit,
    onSaveAppointment: (Appointment) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var selectedAppointmentToEdit by remember { mutableStateOf<Appointment?>(null) }

    val filteredAppointments = remember(appointments, searchQuery) {
        if (searchQuery.isBlank()) {
            appointments
        } else {
            appointments.filter {
                it.clientName.contains(searchQuery, ignoreCase = true) ||
                        it.clientPhone.contains(searchQuery, ignoreCase = true) ||
                        it.serviceName.contains(searchQuery, ignoreCase = true) ||
                        it.professionalName.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search & Add Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Buscar por cliente, serviço...", fontSize = 14.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Limpar")
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .testTag("admin_search_field"),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color(0xFFEFD8DE)
                ),
                singleLine = true
            )

            Button(
                onClick = { showAddDialog = true },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                modifier = Modifier.testTag("admin_add_appointment_button")
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Encaixe", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Total na Agenda: ${filteredAppointments.size} atendimentos",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (filteredAppointments.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.EventNote,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Nenhum agendamento encontrado",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredAppointments) { appt ->
                    AdminAppointmentCard(
                        appointment = appt,
                        onDelete = { onDeleteAppointment(appt.id) },
                        onEdit = {
                            selectedAppointmentToEdit = appt
                            showEditDialog = true
                        }
                    )
                }
            }
        }
    }

    // Add Appointment Dialog
    if (showAddDialog) {
        AdminAddEditDialog(
            title = "Lançar Encaixe Manual",
            onDismiss = { showAddDialog = false },
            onSave = { newAppt ->
                onSaveAppointment(newAppt)
                showAddDialog = false
            }
        )
    }

    // Edit Appointment Dialog
    if (showEditDialog && selectedAppointmentToEdit != null) {
        AdminAddEditDialog(
            title = "Reagendar / Editar",
            appointment = selectedAppointmentToEdit,
            onDismiss = {
                showEditDialog = false
                selectedAppointmentToEdit = null
            },
            onSave = { updatedAppt ->
                onSaveAppointment(updatedAppt)
                showEditDialog = false
                selectedAppointmentToEdit = null
            }
        )
    }
}

@Composable
fun AdminAppointmentCard(
    appointment: Appointment,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val clipboardManager = androidx.compose.ui.platform.LocalClipboardManager.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = appointment.clientName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = appointment.clientPhone,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                // Service cost pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFD8E4))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "R$ %.2f".format(appointment.servicePrice),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF31101B)
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = Color(0xFFEFD8DE)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCut,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = appointment.serviceName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = appointment.professionalName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = appointment.date,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = appointment.time,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = Color(0xFFEFD8DE).copy(alpha = 0.5f)
            )

            // Actions row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Copy WhatsApp Reminder
                IconButton(
                    onClick = {
                        val msg = "Olá, ${appointment.clientName}! Confirmamos seu horário de ${appointment.serviceName} com ${appointment.professionalName} no dia ${appointment.date} às ${appointment.time} no Salão BellaVita! Estamos ansiosos para te ver. ✨"
                        clipboardManager.setText(androidx.compose.ui.text.AnnotatedString(msg))
                        android.widget.Toast.makeText(context, "Modelo WhatsApp Copiado!", android.widget.Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copiar Lembrete",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = Color(0xFF495D92),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir",
                        tint = Color(0xFFBA1A1A),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AdminAddEditDialog(
    title: String,
    appointment: Appointment? = null,
    onDismiss: () -> Unit,
    onSave: (Appointment) -> Unit
) {
    var clientName by remember { mutableStateOf(appointment?.clientName ?: "") }
    var clientPhone by remember { mutableStateOf(appointment?.clientPhone ?: "") }
    var selectedService by remember { mutableStateOf(appointment?.serviceName ?: "Corte & Escova") }
    var selectedProfessional by remember { mutableStateOf(appointment?.professionalName ?: "Gabriela Silva") }
    var date by remember { mutableStateOf(appointment?.date ?: "") }
    var time by remember { mutableStateOf(appointment?.time ?: "10:00") }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    if (date.isEmpty()) {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        date = format.format(Calendar.getInstance().time)
    }

    val servicePrices = remember {
        mapOf(
            "Corte & Escova" to 120.00,
            "Coloração Premium" to 220.00,
            "Limpeza de Pele" to 150.00,
            "Massagem Relaxante" to 180.00,
            "Design de Sobrancelhas" to 60.00,
            "Manicure & Pedicure" to 80.00
        )
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (errorMsg != null) {
                    Text(
                        text = errorMsg!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedTextField(
                    value = clientName,
                    onValueChange = { clientName = it },
                    label = { Text("Nome do Cliente") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = clientPhone,
                    onValueChange = { clientPhone = it },
                    label = { Text("Telefone / WhatsApp") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Service Selector Buttons
                Text("Serviço:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    servicePrices.keys.chunked(2).forEach { chunk ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            chunk.forEach { svc ->
                                val selected = selectedService == svc
                                FilterChip(
                                    selected = selected,
                                    onClick = { selectedService = svc },
                                    label = { Text(svc, fontSize = 11.sp) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                // Professional Selector Buttons
                Text("Especialista:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    listOf("Gabriela Silva", "Juliana Costa", "Clara Mendes", "Ana Souza").chunked(2).forEach { chunk ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            chunk.forEach { prof ->
                                val selected = selectedProfessional == prof
                                FilterChip(
                                    selected = selected,
                                    onClick = { selectedProfessional = prof },
                                    label = { Text(prof, fontSize = 11.sp) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = date,
                        onValueChange = { date = it },
                        label = { Text("Data") },
                        placeholder = { Text("DD/MM/AAAA") },
                        modifier = Modifier.weight(1.2f)
                    )
                    OutlinedTextField(
                        value = time,
                        onValueChange = { time = it },
                        label = { Text("Hora") },
                        placeholder = { Text("HH:MM") },
                        modifier = Modifier.weight(0.8f)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (clientName.isBlank()) {
                        errorMsg = "Por favor, insira o nome."
                        return@Button
                    }
                    if (clientPhone.isBlank()) {
                        errorMsg = "Por favor, insira o telefone."
                        return@Button
                    }
                    if (date.isBlank() || time.isBlank()) {
                        errorMsg = "Preencha a data e hora."
                        return@Button
                    }

                    val price = servicePrices[selectedService] ?: 100.00
                    val updatedOrNew = Appointment(
                        id = appointment?.id ?: 0,
                        clientName = clientName,
                        clientPhone = clientPhone,
                        serviceName = selectedService,
                        servicePrice = price,
                        professionalName = selectedProfessional,
                        date = date,
                        time = time,
                        timestamp = appointment?.timestamp ?: System.currentTimeMillis()
                    )
                    onSave(updatedOrNew)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        shape = RoundedCornerShape(28.dp)
    )
}

@Composable
fun AdminMetricsTab(appointments: List<Appointment>) {
    var costInput by remember { mutableStateOf("") }
    var paidInput by remember { mutableStateOf("") }
    var changeResult by remember { mutableStateOf<Double?>(null) }
    var breakdownCoins by remember { mutableStateOf<List<String>>(emptyList()) }

    val totalCount = appointments.size
    val totalRevenue = appointments.sumOf { it.servicePrice }
    val averageTicket = if (totalCount > 0) totalRevenue / totalCount else 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Resumo Geral de Caixa",
            fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )

        // Metrics Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricsMetricCard(
                title = "Faturamento Estimado",
                value = "R$ %.2f".format(totalRevenue),
                icon = Icons.Default.TrendingUp,
                tint = Color(0xFF386A20),
                modifier = Modifier.weight(1f)
            )
            MetricsMetricCard(
                title = "Agendamentos",
                value = totalCount.toString(),
                icon = Icons.Default.EventNote,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
        }

        MetricsMetricCard(
            title = "Ticket Médio por Cliente",
            value = "R$ %.2f".format(averageTicket),
            icon = Icons.Default.AttachMoney,
            tint = Color(0xFF31101B),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Troco Calculator Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp)),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Calculadora de Troco",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Text(
                    text = "Ferramenta para auxiliar o fechamento físico no balcão do salão.",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = costInput,
                        onValueChange = { costInput = it },
                        label = { Text("Valor do Serviço", fontSize = 11.sp) },
                        placeholder = { Text("Ex: 120") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = paidInput,
                        onValueChange = { paidInput = it },
                        label = { Text("Valor Recebido", fontSize = 11.sp) },
                        placeholder = { Text("Ex: 150") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val cost = costInput.toDoubleOrNull() ?: 0.0
                        val paid = paidInput.toDoubleOrNull() ?: 0.0
                        if (paid >= cost) {
                            val change = paid - cost
                            changeResult = change

                            // Calculate recommended bill breakdown
                            val list = mutableListOf<String>()
                            var remaining = change
                            val notes = listOf(100, 50, 20, 10, 5, 2)
                            notes.forEach { note ->
                                val count = (remaining / note).toInt()
                                if (count > 0) {
                                    list.add("$count nota(s) de R$ $note")
                                    remaining %= note
                                }
                            }
                            if (remaining > 0.01) {
                                list.add("R$ %.2f em moedas".format(remaining))
                            }
                            breakdownCoins = list
                        } else {
                            changeResult = null
                            breakdownCoins = emptyList()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Calcular Troco", fontWeight = FontWeight.Bold)
                }

                if (changeResult != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                text = "Troco Devido: R$ %.2f".format(changeResult),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            if (breakdownCoins.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Sugestão de entrega:",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                breakdownCoins.forEach { line ->
                                    Text(text = "• $line", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MetricsMetricCard(
    title: String,
    value: String,
    icon: ImageVector,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun AdminStaffTab() {
    var activeStaff by remember { mutableStateOf(mapOf(
        "Gabriela Silva" to "Ativo",
        "Juliana Costa" to "Pausa",
        "Clara Mendes" to "Ativo",
        "Ana Souza" to "Em Atendimento"
    )) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Disponibilidade da Equipe",
            fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Monitore e altere o status de trabalho de cada especialista em tempo real.",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        listOf(
            Triple("Gabriela Silva", "👩🏻‍🦱 Cabelo & Penteados", "Especialista em mechas, corte moderno e hidratações reconstrutivas."),
            Triple("Juliana Costa", "👱🏻‍♀️ Unhas & Manicure", "Nail designer profissional, unhas em gel e esmaltação premium."),
            Triple("Clara Mendes", "👩🏼‍💼 Maquiagem de Noivas", "Maquiagens sofisticadas de noivas, debutantes e eventos de alta costura."),
            Triple("Ana Souza", "👩🏽‍⚕️ Estética Facial", "Tratamentos de rejuvenescimento, peelings suaves e microagulhamento.")
        ).forEach { staff ->
            val name = staff.first
            val specialty = staff.second
            val desc = staff.third
            val currentStatus = activeStaff[name] ?: "Ativo"

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = specialty,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        // Status pill
                        val (bgColor, textColor) = when (currentStatus) {
                            "Ativo" -> Color(0xFFE8F5E9) to Color(0xFF2E7D32)
                            "Em Atendimento" -> Color(0xFFFFF3E0) to Color(0xFFEF6C00)
                            else -> Color(0xFFECEFF1) to Color(0xFF37474F)
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(bgColor)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = currentStatus,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = desc,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Status Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Ativo", "Em Atendimento", "Pausa").forEach { statusOption ->
                            val isSelected = currentStatus == statusOption
                            ElevatedFilterChip(
                                selected = isSelected,
                                onClick = {
                                    val newMap = activeStaff.toMutableMap()
                                    newMap[name] = statusOption
                                    activeStaff = newMap
                                },
                                label = { Text(statusOption, fontSize = 10.sp) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AdminAiTab(
    appointments: List<Appointment>,
    adminAiQuery: String,
    adminAiResponse: String,
    isAdminAiLoading: Boolean,
    onUpdateAdminAiQuery: (String) -> Unit,
    onGetAdminAssistance: (String) -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val clipboardManager = androidx.compose.ui.platform.LocalClipboardManager.current

    val contextSummary = remember(appointments) {
        val total = appointments.size
        val tomorrowCount = appointments.count { it.date.contains("/") } // Simple heuristic for date
        val revenue = appointments.sumOf { it.servicePrice }
        "O salão possui $total agendamentos cadastrados na agenda do banco de dados Room. O faturamento acumulado atual é de R$ $revenue."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Bella AI • Assistente de Equipe",
            fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Ferramenta inteligente para criar lembretes cordiais de WhatsApp, gerenciar situações com clientes e criar campanhas de marketing rápidos.",
            fontSize = 13.sp,
            color = Color.Gray
        )

        // Suggestion chips
        Text(
            text = "Perguntas e Ações Rápidas:",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )

        val suggestions = listOf(
            "Crie um lembrete elegante de WhatsApp amigável para enviar no dia anterior",
            "Cortei 10% no valor para novos clientes. Redija anúncio para Instagram",
            "Como contornar com elegância uma cliente que chegou 15 minutos atrasada?",
            "Dicas para motivar minha equipe hoje no salão"
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            suggestions.forEach { query ->
                Card(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            onUpdateAdminAiQuery(query)
                            onGetAdminAssistance(contextSummary)
                        },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD8E4).copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = query,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF31101B),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = adminAiQuery,
            onValueChange = onUpdateAdminAiQuery,
            placeholder = { Text("Escreva sua pergunta de gestão ou modelo de mensagem aqui...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .testTag("admin_ai_query_input"),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color(0xFFEFD8DE)
            ),
            maxLines = 4
        )

        Button(
            onClick = { onGetAdminAssistance(contextSummary) },
            enabled = adminAiQuery.isNotBlank() && !isAdminAiLoading,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            if (isAdminAiLoading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
            } else {
                Text("Obter Auxílio Inteligente", fontWeight = FontWeight.Bold)
            }
        }

        if (adminAiResponse.isNotEmpty()) {
            Text(
                text = "Retorno da Bella AI:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFEFD8DE), RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = adminAiResponse,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            clipboardManager.setText(androidx.compose.ui.text.AnnotatedString(adminAiResponse))
                            android.widget.Toast.makeText(context, "Retorno Copiado!", android.widget.Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copiar Texto", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.FlowRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement
    ) {
        content()
    }
}
