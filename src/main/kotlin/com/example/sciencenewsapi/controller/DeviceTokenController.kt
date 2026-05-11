package com.example.sciencenewsapi.controller

import com.example.sciencenewsapi.dto.device.RegisterDeviceTokenRequest
import com.example.sciencenewsapi.service.DeviceTokenService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/devices")
class DeviceTokenController(
    private val service: DeviceTokenService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterDeviceTokenRequest): String {
        return service.register(request)
    }
}