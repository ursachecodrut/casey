package com.codrutursache.casey

import android.app.Application
import com.codrutursache.casey.device.NotificationScheduler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CaseyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        NotificationScheduler.createNotificationChannel(this)

        NotificationScheduler.scheduleNotification(this)
    }

}