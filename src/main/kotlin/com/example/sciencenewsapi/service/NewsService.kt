package com.example.sciencenewsapi.service

import com.example.sciencenewsapi.Entity.NewsArticle
import com.example.sciencenewsapi.repository.NewsArticleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class NewsService(
    private val repo: NewsArticleRepository
) {

    fun getNews(
        page: Int,
        size: Int,
        search: String?,
        section: String?
    ): Page<NewsArticle> {

        val pageable = PageRequest.of(
            page,
            size,
            Sort.by(Sort.Direction.DESC, "publishedAt")
        )

        return when {
            !search.isNullOrBlank() ->
                repo.findByTitleContainingIgnoreCase(search, pageable)

            !section.isNullOrBlank() ->
                repo.findBySectionIgnoreCase(section, pageable)

            else ->
                repo.findAll(pageable)
        }
    }
}