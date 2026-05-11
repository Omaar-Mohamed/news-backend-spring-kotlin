package com.example.sciencenewsapi.Entity


import jakarta.persistence.*

@Entity
@Table(name = "device_tokens")
data class DeviceToken(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    val token: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: AppUser
)
