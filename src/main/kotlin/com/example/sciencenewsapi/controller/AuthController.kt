package com.example.sciencenewsapi.controller



import com.example.sciencenewsapi.dto.auth.AuthResponse
import com.example.sciencenewsapi.dto.auth.LoginRequest
import com.example.sciencenewsapi.dto.auth.RegisterRequest
import com.example.sciencenewsapi.service.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val service: AuthService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): AuthResponse {
        return service.register(request)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): AuthResponse {
        return service.login(request)
    }
}