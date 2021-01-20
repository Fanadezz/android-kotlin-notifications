package com.example.android.eggtimernotifications

import android.app.NotificationManager
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFirebaseMessagingService: FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
    super.onMessageReceived(remoteMessage)
        Timber.i("From ${remoteMessage.from}")

        //TODO Step 3.5 check messages for data
        remoteMessage.data.let {
            Timber.i("Message data payload: ${remoteMessage.data}")
        }

        //TODO: Step. 3.6 check messages for notification and call sentNotification

        remoteMessage.notification?.let {

            Timber.i("Message Notification Body: ${it.body}")
            sendNotification(it.body!!)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.i("Refreshed Token: $token")



        //pretend to send token
//sendRegistrationToServer(token)
    }

private fun sendNotification(messageBody: String){

    val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class
            .java) as NotificationManager

    notificationManager.sendNotification(messageBody, applicationContext)
}

}