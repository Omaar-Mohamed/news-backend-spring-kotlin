package com.example.sciencenewsapi.service

import com.example.sciencenewsapi.Entity.AppUser
import com.example.sciencenewsapi.Entity.Role

import com.example.sciencenewsapi.dto.guardian.auth.AuthResponse
import com.example.sciencenewsapi.dto.guardian.auth.LoginRequest
import com.example.sciencenewsapi.dto.guardian.auth.RegisterRequest

import com.example.sciencenewsapi.repository.AppUserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val repo: AppUserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    fun register(request: RegisterRequest): AuthResponse {
        val role = request.role
            ?.let { Role.valueOf(it.uppercase()) }
            ?: Role.USER

        val user = AppUser(
            email = request.email,
            password = passwordEncoder.encode(request.password ).toString(),
            role = role
        )

        repo.save(user)

        return AuthResponse(
            token = jwtService.generateToken(
                email = user.email,
                role = user.role.name
            )
        )
    }

    fun login(request: LoginRequest): AuthResponse {
        val user = repo.findByEmail(request.email)
            ?: throw RuntimeException("Invalid email or password")

        val passwordMatches = passwordEncoder.matches(
            request.password,
            user.password
        )

        if (!passwordMatches) {
            throw RuntimeException("Invalid email or password")
        }

        return AuthResponse(
            token = jwtService.generateToken(
                email = user.email,
                role = user.role.name
            )
        )
    }
}