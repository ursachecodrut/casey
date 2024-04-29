package com.codrutursache.casey

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CaseyApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
//
//        createNotificationChannel()
//
//        scheduleDebugNotification(this)
    }

//    private fun createNotificationChannel() {
//        val name = getString(R.string.notification_channel_name)
//        val descriptionText = getString(R.string.notification_channel_description)
//        val channelId = getString(R.string.notification_channel_id)
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//
//        val channel = NotificationChannel(channelId, name, importance).apply {
//            description = descriptionText
//        }
//
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//
//        Log.d("NotificationChannel", "Notification channel created")
//    }
//
//    fun scheduleDebugNotification(context: Context) {
//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val intent = Intent(context, DailyReminderReceiver::class.java)
//        val pendingIntent =
//            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//        // Set the alarm to start immediately and repeat every minute
//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            System.currentTimeMillis(),
//            60 * 1000, // Repeat every minute
//            pendingIntent
//        )
//
//        Log.d("DebugNotification", "Debug notification scheduled")
//    }
}