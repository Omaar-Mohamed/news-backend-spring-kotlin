package com.example.sciencenewsapi.controller


import com.example.sciencenewsapi.dto.guardian.SyncNewsResponse
import com.example.sciencenewsapi.service.NewsSyncService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminController(
    private val service: NewsSyncService
) {

    @PostMapping("/sync")
    fun syncNews(): SyncNewsResponse {
       return service.sync()
    }
}