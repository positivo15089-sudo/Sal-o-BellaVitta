package com.example.data

data class Service(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val durationMinutes: Int,
    val category: String,
    val iconName: String
)

data class Professional(
    val id: String,
    val name: String,
    val specialty: String,
    val rating: Float,
    val reviewsCount: Int,
    val availableHours: List<String>
)

object SalonData {
    val services = listOf(
        Service(
            id = "s1",
            name = "Corte & Escova",
            description = "Corte feminino/masculino personalizado com lavagem e escova modeladora.",
            price = 120.00,
            durationMinutes = 60,
            category = "Cabelo",
            iconName = "content_cut"
        ),
        Service(
            id = "s2",
            name = "Coloração Premium",
            description = "Coloração completa com produtos de alta qualidade e tratamento reconstrutor pós-química.",
            price = 250.00,
            durationMinutes = 120,
            category = "Cabelo",
            iconName = "palette"
        ),
        Service(
            id = "s3",
            name = "Manicure & Pedicure",
            description = "Cuidado completo com esmaltação nacional/importada, hidratação de cutículas e massagem nas mãos.",
            price = 70.00,
            durationMinutes = 45,
            category = "Unhas",
            iconName = "brush"
        ),
        Service(
            id = "s4",
            name = "Maquiagem Social",
            description = "Maquiagem profissional resistente de alta definição para festas, casamentos e formaturas.",
            price = 180.00,
            durationMinutes = 60,
            category = "Estética",
            iconName = "face"
        ),
        Service(
            id = "s5",
            name = "Limpeza de Pele Profunda",
            description = "Tratamento facial com extração de cravos, esfoliação ultrassônica e máscara de hidratação profunda.",
            price = 150.00,
            durationMinutes = 75,
            category = "Estética",
            iconName = "spa"
        ),
        Service(
            id = "s6",
            name = "Massagem Relaxante",
            description = "Terapia corporal relaxante com óleos essenciais aromáticos para alívio total do estresse e tensões musculares.",
            price = 160.00,
            durationMinutes = 60,
            category = "Massagem",
            iconName = "favorite"
        )
    )

    val professionals = listOf(
        Professional(
            id = "p1",
            name = "Gabriela Silva",
            specialty = "Corte, Design & Cor",
            rating = 4.9f,
            reviewsCount = 142,
            availableHours = listOf("09:00", "10:30", "13:00", "14:30", "16:00", "17:30")
        ),
        Professional(
            id = "p2",
            name = "Mariana Souza",
            specialty = "Especialista em Unhas & Gel",
            rating = 4.8f,
            reviewsCount = 98,
            availableHours = listOf("09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00")
        ),
        Professional(
            id = "p3",
            name = "Juliana Costa",
            specialty = "Maquiadora Profissional & Visagista",
            rating = 5.0f,
            reviewsCount = 76,
            availableHours = listOf("10:00", "11:30", "14:00", "15:30", "17:00")
        ),
        Professional(
            id = "p4",
            name = "Lucas Oliveira",
            specialty = "Massoterapeuta & Esteticista Corporal",
            rating = 4.9f,
            reviewsCount = 64,
            availableHours = listOf("09:30", "11:00", "13:30", "15:00", "16:30")
        )
    )
}
