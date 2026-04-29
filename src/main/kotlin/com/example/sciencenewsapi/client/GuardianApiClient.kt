package com.example.sciencenewsapi.client

import com.example.sciencenewsapi.dto.guardian.GuardianResponseDto
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class GuardianApiClient {

    private val restTemplate = RestTemplate()

    fun fetchScienceNews(): GuardianResponseDto? {
        val url = "https://content.guardianapis.com/search?section=science&api-key=test"
        return restTemplate.getForObject(url,  GuardianResponseDto::class.java)
    }
}