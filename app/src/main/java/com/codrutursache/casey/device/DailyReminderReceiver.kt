package com.codrutursache.casey.device

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.codrutursache.casey.R

//class DailyReminderReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context?, intent: Intent?) {
//        with(NotificationManagerCompat.from(context!!)) {
//            if (ActivityCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.POST_NOTIFICATIONS
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                // ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                // public fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
//                //                                        grantResults: IntArray)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                Log.e("DailyReminderReceiver", "No permission to post notifications")
//
//                return@with
//            }
//            // notificationId is a unique int for each notification that you must define.
//
//            val builder = NotificationCompat.Builder(
//                context,
//                context.getString(R.string.notification_channel_id)
//            )
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle("My notification")
//                .setContentText("Hello World!")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setAutoCancel(true)
//
//            notify(0, builder.build())
//            Log.d("DailyReminderReceiver", "Notification posted")
//        }
//    }
//}