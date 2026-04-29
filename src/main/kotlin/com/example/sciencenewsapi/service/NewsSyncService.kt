package com.example.sciencenewsapi.service

import com.example.sciencenewsapi.Entity.NewsArticle
import com.example.sciencenewsapi.client.GuardianApiClient
import com.example.sciencenewsapi.dto.guardian.SyncNewsResponse
import com.example.sciencenewsapi.repository.NewsArticleRepository

import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class NewsSyncService(
    private val client: GuardianApiClient,
    private val repo: NewsArticleRepository
) {

    fun sync(): SyncNewsResponse {
        val response = client.fetchScienceNews()
        val articles = response?.response?.results ?: emptyList()

        val newArticles = articles
            .filter { article -> !repo.existsByUrl(article.webUrl) }
            .map { article ->
                NewsArticle(
                    title = article.webTitle,
                    description = null,
                    url = article.webUrl,
                    source = "The Guardian",
                    section = article.sectionName,
                    publishedAt = OffsetDateTime
                        .parse(article.webPublicationDate)
                        .toLocalDateTime()
                )
            }

        repo.saveAll(newArticles)

        return if (newArticles.isEmpty()) {
            SyncNewsResponse(
                message = "No new articles. Everything is already updated.",
                addedCount = 0,
                skippedCount = articles.size
            )
        } else {
            SyncNewsResponse(
                message = "Sync completed successfully.",
                addedCount = newArticles.size,
                skippedCount = articles.size - newArticles.size
            )
        }
    }
}