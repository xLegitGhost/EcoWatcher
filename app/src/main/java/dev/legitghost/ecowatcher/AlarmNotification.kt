package dev.legitghost.ecowatcher

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlarmNotification : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        reminderNotification(context)
    }

    private fun reminderNotification(context: Context?) {
        val notification = NotificationCompat.Builder(context!!, "ecowatcher_reminder")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("¡Tienes que sacar la basura!")
            .setContentText("Es hora de sacar la basura, no te olvides de hacerlo.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, notification.build())
    }



}