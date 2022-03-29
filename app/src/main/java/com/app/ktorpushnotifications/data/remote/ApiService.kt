package com.app.ktorpushnotifications.data.remote

interface ApiService {
    suspend fun sendNotification(title: String, description: String)
    companion object {
        const val SEND_NOTIFICATION = "http://192.168.0.102:8080/"
    }
}