package com.example.pushup;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    private String CHANNEL_ID = "APP_ID";
    static int count=1;
    static NotificationManagerCompat notificationManager = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Javed", "onReceive called");
        notificationManager = NotificationManagerCompat.from(context);
        createNotificationChannel("Notification_channel", "Channel description", notificationManager);

        Intent resultIntent=new Intent(context,NewTask.class);
        resultIntent.putExtra("command","PlayA");
        PendingIntent resultPendingIntent=PendingIntent.getActivity(context,0,resultIntent,PendingIntent.FLAG_CANCEL_CURRENT);

        String bigText = "Утренний свет, когда он прикоснулся ко мне,\n" +
                "Он был там, но я не мог увидеть.\n" +
                "Блуждать ли мне, как блудному сыну?\n" +
                "Станет ли охотник жертвой дымящегося ружья?";
/*
        NotificationCompat.Builder BigText =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("RDR2")
                        .setContentText("Пора надевать каску!!!")
                        .setAutoCancel(true)
                        .setShowWhen(true)
                        .setContentIntent(resultPendingIntent)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));

        notificationManager.notify(count,BigText.build());


        NotificationCompat.Builder BigPicture =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Бомбардировка")
                        .setContentText("Пора надевать каску!!!")
                        .setAutoCancel(true)
                        .setShowWhen(true)
                        .setContentIntent(resultPendingIntent)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.bigpicture)));

        notificationManager.notify(count+1,BigPicture.build());
*/

        NotificationCompat.Builder ThreeBtn =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("RDR2")
                        .setContentText("Пора надевать каску!!!")
                        .setAutoCancel(true)
                        .setShowWhen(true)
                        .setContentIntent(resultPendingIntent)
                        .addAction(R.drawable.bigpicture,"Открыть",resultPendingIntent)
                        .addAction(R.drawable.ic_launcher_background,"Отказаться",null)
                        .addAction(R.mipmap.ic_launcher_round,"Другой вариант",null);

        notificationManager.notify(count,ThreeBtn.build());


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND,10);
        Intent intent1 = new Intent(context, AlarmReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent1, 0);
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);



    }

    private void createNotificationChannel(String channelName, String channelDescription, NotificationManagerCompat nm) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);

            nm.createNotificationChannel(channel);
        }
    }
}
