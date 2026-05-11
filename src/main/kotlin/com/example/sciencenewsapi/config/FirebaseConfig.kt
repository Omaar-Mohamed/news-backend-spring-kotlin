package com.example.sciencenewsapi.config


import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FirebaseConfig {

    @Bean
    fun firebaseApp(): FirebaseApp {

        val serviceAccount =
            FileInputStream("secrets/firebase-service-account.json")

        val options = FirebaseOptions.builder()
            .setCredentials(
                GoogleCredentials.fromStream(serviceAccount)
            )
            .build()

        return FirebaseApp.initializeApp(options)
    }
}