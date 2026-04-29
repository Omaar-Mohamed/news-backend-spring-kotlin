package com.example.sciencenewsapi.dto.guardian


data class GuardianResponseDto(
    val response: GuardianResponseDataDto
)

data class GuardianResponseDataDto(
    val status: String,
    val total: Int,
    val pageSize: Int,
    val currentPage: Int,
    val pages: Int,
    val results: List<GuardianArticleDto>
)

data class GuardianArticleDto(
    val id: String,
    val type: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
    val apiUrl: String,
    val pillarName: String?
)
