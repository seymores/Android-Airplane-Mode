package org.seymourcakes.airplane;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * 
 * @author seymores@gmail.com
 * 
 */
public class MainWidget extends AppWidgetProvider {

	private static final String TAG = "MainWidget";
	private static final String AIRPLANE_MODE = "airplanemodeupdate";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		//Log.d(TAG, " *** onUpdate");

		Intent receiver = new Intent(context, AirplaneModeReceiver.class);
		receiver.setAction(AIRPLANE_MODE);
		receiver.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
		
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				receiver, 0);
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);
		views.setOnClickPendingIntent(R.id.button, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, views);

	}

}
