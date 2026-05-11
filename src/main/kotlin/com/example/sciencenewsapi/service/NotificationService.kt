package com.example.sciencenewsapi.service

import com.example.sciencenewsapi.Entity.Role
import com.example.sciencenewsapi.repository.DeviceTokenRepository
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val deviceTokenRepo: DeviceTokenRepository
) {

    fun sendToUsers(title: String, body: String): Int {
        val tokens = deviceTokenRepo.findByUserRole(Role.USER)

        println("USER device tokens count = ${tokens.size}")

        tokens.forEach { deviceToken ->
            try {
                val message = Message.builder()
                    .setToken(deviceToken.token)
                    .setNotification(
                        Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build()
                    )
                    .build()

                val result = FirebaseMessaging.getInstance().send(message)
                println("Notification sent successfully: $result")

            } catch (e: Exception) {
                println("Failed to send notification to token: ${deviceToken.token}")
                e.printStackTrace()
            }
        }

        return tokens.size
    }
}