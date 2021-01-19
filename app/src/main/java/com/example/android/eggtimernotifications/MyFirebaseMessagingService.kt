package com.example.android.eggtimernotifications

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFirebaseMessagingService: FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.i("From ${remoteMessage.from}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.i("Refreshed Token: $token")

        //pretend to send token

    }
}