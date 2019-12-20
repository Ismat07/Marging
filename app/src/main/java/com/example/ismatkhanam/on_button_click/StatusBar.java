package com.example.ismatkhanam.on_button_click;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class StatusBar extends Activity {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 79438;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusbar);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }

    public void NotiButtonClicked(View view){
        //Build the notification
        notification.setSmallIcon(R.drawable.lightning);
        notification.setTicker("This is a ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Here is a title");
        notification.setContentText("I am the body text of ur notification");

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        notification.setContentIntent(pendingIntent);

        //Build notification and issues it
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
    }
}
