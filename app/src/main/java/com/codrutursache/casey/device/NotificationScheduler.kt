package com.codrutursache.casey.device

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.codrutursache.casey.R

object NotificationScheduler {


    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context: Context) {
        val name = context.getString(R.string.notification_channel_name)
        val descriptionText = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel =
            NotificationChannel(
                context.getString(R.string.notification_channel_id),
                name,
                importance
            ).apply {
                description = descriptionText
            }
        val notificationManager =
            context.getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        Log.d("[DEBUG] Notification Scheduler", "Notification channel created")
    }

    fun scheduleNotification(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationBroadcastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_IMMUTABLE)

        // Set the alarm to start at approximately 13:00 every day
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, Properties.HOUR_OF_DAY)
            set(Calendar.MINUTE, Properties.MINUTE)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

//         For testing purposes, set the alarm to start every 30 seconds
//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            System.currentTimeMillis(),
//            30 * 1000,
//            pendingIntent
//        )

        Log.d("[DEBUG] Notification Scheduler", "Notification scheduled")
    }


    private object Properties {
        const val HOUR_OF_DAY = 13
        const val MINUTE = 0
    }

}