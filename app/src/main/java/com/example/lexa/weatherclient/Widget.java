package com.example.lexa.weatherclient;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by lexa on 23.03.16.
 */
public class Widget extends AppWidgetProvider {
    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWeatherWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);

        //Подготавливаем Intent для Broadcast
        Intent update = new Intent(context, Widget.class);
        update.setAction(ACTION_WIDGET_RECEIVER);
        update.putExtra("msg", "You Click Update");

        //создаем наше событие
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, R.id.imageView, update, 0);
        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.imageView, actionPendingIntent);

        Intent settings = new Intent(context, Widget.class);
        settings.setAction(ACTION_WIDGET_RECEIVER);
        settings.putExtra("msg", "You Click Settings");

        //создаем наше событие
        PendingIntent actionPendingIntent1 = PendingIntent.getBroadcast(context, R.id.imageView3, settings, 0);

        //регистрируем наше событие
        remoteViews.setOnClickPendingIntent(R.id.imageView3, actionPendingIntent1);



        //обновляем виджет
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Ловим наш Broadcast, проверяем и выводим сообщение
        final String action = intent.getAction();
        if (ACTION_WIDGET_RECEIVER.equals(action)) {
            String msg = "null";
            try {
                msg = intent.getStringExtra("msg");
            } catch (NullPointerException e) {
                Log.e("Error", "msg = null");
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }
}
