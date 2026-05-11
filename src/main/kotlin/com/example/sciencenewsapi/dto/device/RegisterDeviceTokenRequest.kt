package com.example.sciencenewsapi.dto.device

import jakarta.validation.constraints.NotBlank

data class RegisterDeviceTokenRequest(
    @field:NotBlank(message = "Device token is required")
    val token: String
)