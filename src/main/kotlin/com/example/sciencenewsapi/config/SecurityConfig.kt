package com.example.sciencenewsapi.config


import com.example.sciencenewsapi.security.JwtAuthFilter
import com.example.sciencenewsapi.service.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtService: JwtService

) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        val jwtFilter = JwtAuthFilter(jwtService)

        return http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling { exceptions ->
                exceptions
                    .authenticationEntryPoint { _, response, _ ->
                        response.status = 401
                        response.contentType = "application/json"
                        response.writer.write(
                            """
                {
                  "success": false,
                  "statusCode": 401,
                  "errorType": "UNAUTHORIZED",
                  "message": "Authentication token is missing or invalid"
                }
                """.trimIndent()
                        )
                    }
                    .accessDeniedHandler { _, response, _ ->
                        response.status = 403
                        response.contentType = "application/json"
                        response.writer.write(
                            """
                {
                  "success": false,
                  "statusCode": 403,
                  "errorType": "FORBIDDEN",
                  "message": "You do not have permission to access this resource"
                }
                """.trimIndent()
                        )
                    }
            }
            .build()
    }
}