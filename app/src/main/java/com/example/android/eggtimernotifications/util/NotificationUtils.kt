/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.android.eggtimernotifications.MainActivity
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0 /*FLAG_ONE_SHOT
Flag indicating that this PendingIntent can be used only once.*/

// TODO: Step 1.1 extension function to send messages (GIVEN)
/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    // Create the content intent for the notification, which launches
    // this activity
    // TODO: Step 1.11 create intent

    //PendingIntent's base intent pass in the context and the activity to be launched
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // TODO: Step 1.12 create PendingIntent


    val pendingIntent = PendingIntent.getActivity(
            applicationContext, // -> context in which this PI should start the activity
            NOTIFICATION_ID, // -> private request code for the sender
            contentIntent, // -> base intent/ intent of the activity to be launched
            PendingIntent.FLAG_CANCEL_CURRENT) // -> flag to control  behaviour of
    // multiple PendingIntents


    // TODO: Step 2.0 add style
    val eggImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.cooked_egg)

    val bigPicStyle =
            NotificationCompat.BigPictureStyle()
                    .bigPicture(eggImage)
                    .bigLargeIcon(null) //set to null to make the large icon disappear when the notification
    // is expanded

    //TODO: Step 2.2 add snooze PendingIntent
    //pending intent  to schedule a new alarm and post a new notification after 60 secs

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)

    //pendingIntent with ONE_SHOT_FLAG for one-time use

    val snoozePendingIntent =
            PendingIntent.getBroadcast(applicationContext, REQUEST_CODE, snoozeIntent, FLAGS)
    // TODO: Step 1.2 get an instance of NotificationCompat.Builder
    // Build the notification
    val builder = NotificationCompat.Builder(
            applicationContext, applicationContext.getString(R.string.egg_notification_channel_id))
            // TODO: Step 1.8 use the new 'breakfast' notification channel

            // TODO: Step 1.3 set title, text and icon to builder
            .setSmallIcon(R.drawable.cooked_egg)
            .setContentTitle(applicationContext.resources.getString(R.string.notification_title))
            .setContentText(messageBody)
            // TODO: Step 1.13 set content intent
            .setContentIntent(pendingIntent) //add pending intent
            .setAutoCancel(true) //dismiss the notification

            // TODO: Step 2.1 add style to builder

            .setStyle(bigPicStyle)
            .setLargeIcon(eggImage)


    // TODO: Step 2.3 add snooze action
    .addAction(
            R.drawable.egg_icon, applicationContext.getString(R.string.snooze), snoozePendingIntent)

    // TODO: Step 2.5 set priority

    // TODO: Step 1.4 call notify
    this.notify(NOTIFICATION_ID, builder.build())
}

// TODO: Step 1.14 Cancel all notifications
//extension fxn to cancel all notifications
fun NotificationManager.cancelAllNotifications() {

    this.cancelAll()

}