package com.examplevinhphutvp.fouregroundservice25022020;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private static final

    String CHANNEL_ID = "chanel_01";
    int mNotificationId = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, " onStartCommand", Toast.LENGTH_SHORT).show();
        createNotification();
        return START_STICKY;
    }

    private void createNotification() {
        // Intent : Thao tac voi notification
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder build =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Có thông báo mới")
                        .setContentText("Bạn nhận được thông báo có version mới cho app")
                        .setWhen(System.currentTimeMillis())
                        .setOngoing(true)
                        .setContentIntent(pendingIntent);
        // Hien thi ra notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notifcation";
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            name,
                            NotificationManager.IMPORTANCE_LOW);
            // che độ rung
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        startForeground(1, build.build());
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                stopSelf();
//                stopForeground(true);
//            }
//        },2000);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
//    }
    }
}
