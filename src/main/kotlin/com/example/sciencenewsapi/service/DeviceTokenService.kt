package com.example.sciencenewsapi.service

import com.example.sciencenewsapi.Entity.DeviceToken
import com.example.sciencenewsapi.dto.device.RegisterDeviceTokenRequest
import com.example.sciencenewsapi.repository.AppUserRepository
import com.example.sciencenewsapi.repository.DeviceTokenRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class DeviceTokenService(
    private val deviceTokenRepo: DeviceTokenRepository,
    private val userRepo: AppUserRepository
) {

    fun register(request: RegisterDeviceTokenRequest): String {
//        if (deviceTokenRepo.existsByToken(request.token)) {
//            return "Device token already registered"
//        }

        val email = SecurityContextHolder
            .getContext()
            .authentication
            ?.name

        val user = userRepo.findByEmail(email!!)
            ?: throw RuntimeException("User not found")

        val existingToken = deviceTokenRepo.findByToken(request.token)

        if (existingToken != null) {
            val updatedToken = existingToken.copy(user = user)
            deviceTokenRepo.save(updatedToken)
            return "Device token owner updated successfully"
        }


        deviceTokenRepo.save(
            DeviceToken(
                token = request.token,
                user = user
            )
        )

        return "Device token registered successfully"
    }
}