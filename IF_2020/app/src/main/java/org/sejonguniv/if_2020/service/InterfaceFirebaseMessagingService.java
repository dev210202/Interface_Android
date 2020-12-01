package org.sejonguniv.if_2020.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.RemoteMessage;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.ui.UserMainActivity;
import org.sejonguniv.if_2020.ui.user.home.HomeFragment;

public class InterfaceFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String body = remoteMessage.getNotification().getBody();
            Log.e("Notification Body", body);

            Intent intent = new Intent(this, HomeFragment.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher) // 알림 영역에 노출 될 아이콘.
                    .setContentTitle(getString(R.string.app_name)) // 알림 영역에 노출 될 타이틀
                    .setContentText(body) // Firebase Console 에서 사용자가 전달한 메시지내용
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());


        }
    }

    @Override
    public void onNewToken(String token) {
        Log.e("Token", token);
    }

}
