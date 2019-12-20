package com.example.ismatkhanam.on_button_click;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;
import java.util.Random;

public class PointlessWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Random r = new Random();
        int random_int = r.nextInt(100000000);
        String rand = String.valueOf(random_int);

        final int N = appWidgetIds.length;

        for (int i = 0; i < N; i++){
            int awID = appWidgetIds[i];
            RemoteViews v= new RemoteViews(context.getPackageName(),R.layout.widget);
            v.setTextViewText(R.id.tv_widget_update, rand);
            appWidgetManager.updateAppWidget(awID, v);
        }
    }
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context,"Bye Bye!!!",Toast.LENGTH_SHORT).show();
    }
}
