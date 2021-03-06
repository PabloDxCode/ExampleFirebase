package com.example.pgutierrezd.exampleloginfirebase;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by pgutierrezd on 24/10/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("FIREBASE", "Titulo:" + remoteMessage.getNotification().getTitle()+
                ", "+remoteMessage.getNotification().getTag()
                + ", Mensaje: " + remoteMessage.getNotification().getBody());
    }
}