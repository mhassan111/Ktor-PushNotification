package com.app.ktorpushnotifications

import android.app.Application
import com.onesignal.OneSignal

class PushNotificationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONE_SIGNAL_APP_ID)
    }

    companion object {
        private const val ONE_SIGNAL_APP_ID = "23892033-6eec-4264-acbc-8c17e8ed14e5"
    }
}