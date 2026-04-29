package com.example.sciencenewsapi.controller

import com.example.sciencenewsapi.Entity.NewsArticle
import com.example.sciencenewsapi.service.NewsService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/news")
class NewsController(
    private val service: NewsService
) {

    @GetMapping
    fun getNews(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(required = false) search: String?,
        @RequestParam(required = false) section: String?
    ): Page<NewsArticle> {
        return service.getNews(page, size, search, section)
    }
}