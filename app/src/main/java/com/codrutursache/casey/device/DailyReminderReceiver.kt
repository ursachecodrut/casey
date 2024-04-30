package com.codrutursache.casey.device

import android.Manifest
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.codrutursache.casey.R
import com.codrutursache.casey.domain.model.Resource
import com.codrutursache.casey.domain.repository.NotificationRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationRepository: NotificationRepository

    override fun onReceive(context: Context, intent: Intent?) {

        val action = intent?.action
        if (action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("NotificationBroadcastReceiver", "Boot completed")
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                val text = getFoodTrivia()
                if (text != null) showNotification(context, text)
            }
        }

    }

    private suspend fun getFoodTrivia(): String? {
        return when (val trivia = notificationRepository.getRandomFoodTrivia()) {
            is Resource.Success -> trivia.data?.text
            else -> null
        }
    }

    private fun showNotification(context: Context, text: String) {
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {


                return@with
            }

            val notificationBuilder =
                NotificationCompat.Builder(
                    context,
                    context.getString(R.string.notification_channel_id)
                )
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(context.getString(R.string.food_trivia_notification_title))
                    .setContentText(
                        context.getString(
                            R.string.food_trivia_notification_content,
                            text
                        )
                    )
                    .setStyle(NotificationCompat.BigTextStyle().bigText(text))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)


            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager


            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())

            Log.d("NotificationBroadcastReceiver", "Notification shown")
        }
    }


    companion object {
        const val NOTIFICATION_ID = 1
    }


}