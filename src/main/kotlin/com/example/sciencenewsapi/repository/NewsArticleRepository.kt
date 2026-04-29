package com.example.sciencenewsapi.repository


import com.example.sciencenewsapi.Entity.NewsArticle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
interface NewsArticleRepository : JpaRepository<NewsArticle, Long>{
    fun existsByUrl(url: String): Boolean

    fun findByTitleContainingIgnoreCase(
        title: String,
        pageable: Pageable
    ): Page<NewsArticle>

    fun findBySectionIgnoreCase(
        section: String,
        pageable: Pageable
    ): Page<NewsArticle>

}