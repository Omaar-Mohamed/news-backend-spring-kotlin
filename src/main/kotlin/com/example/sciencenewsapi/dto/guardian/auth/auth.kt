package com.example.sciencenewsapi.dto.guardian.auth

import jakarta.validation.constraints.NotBlank

data class RegisterRequest(
    val email: String,
    @field:NotBlank
    val password: String,
    val role: String? = null
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String
)