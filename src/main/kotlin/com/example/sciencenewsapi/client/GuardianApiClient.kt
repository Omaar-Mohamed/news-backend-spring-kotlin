package com.example.sciencenewsapi.client

import com.example.sciencenewsapi.dto.guardian.GuardianResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class GuardianApiClient(
    @Value("\${guardian.api-key}")
    private val apiKey: String
) {

    private val restTemplate = RestTemplate()

    fun fetchScienceNews(): GuardianResponseDto? {
        val url = UriComponentsBuilder
            .fromUriString("https://content.guardianapis.com/search")
            .queryParam("section", "science")
            .queryParam("api-key", apiKey)
            .toUriString()

        return restTemplate.getForObject(url,  GuardianResponseDto::class.java)
    }
}
