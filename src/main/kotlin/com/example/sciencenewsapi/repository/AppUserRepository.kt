package com.example.sciencenewsapi.repository

import com.example.sciencenewsapi.Entity.AppUser
import org.springframework.data.jpa.repository.JpaRepository

interface AppUserRepository : JpaRepository<AppUser, Long> {
    fun findByEmail(email: String): AppUser?
}