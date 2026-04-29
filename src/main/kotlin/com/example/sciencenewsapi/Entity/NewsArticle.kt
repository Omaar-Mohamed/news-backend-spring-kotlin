package com.example.sciencenewsapi.Entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "news_articles")
data class NewsArticle(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT")
    val description: String? = null,

    @Column(nullable = false, unique = true)
    val url: String,

    val source: String? = null,

    val section: String? = null,

    val publishedAt: LocalDateTime? = null
)