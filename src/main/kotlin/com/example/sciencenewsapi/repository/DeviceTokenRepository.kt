package com.example.sciencenewsapi.repository

import com.example.sciencenewsapi.Entity.DeviceToken
import com.example.sciencenewsapi.Entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceTokenRepository : JpaRepository<DeviceToken, Long> {


    fun existsByToken(token: String): Boolean

    fun findByUserRole(role: Role): List<DeviceToken>
    fun findByToken(token: String): DeviceToken?
}