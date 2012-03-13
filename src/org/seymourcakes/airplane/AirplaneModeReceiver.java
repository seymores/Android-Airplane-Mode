package org.seymourcakes.airplane;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;

public class AirplaneModeReceiver extends BroadcastReceiver {

	private static final String TAG = "AirplaneModeReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {

		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.main);

		boolean isEnabled = Settings.System.getInt(
				context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON,
				0) != 0;

		// toggle airplane mode
		Settings.System.putInt(context.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, isEnabled ? 0 : 1);

		// Post an intent to reload
		Intent changeMode = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		changeMode.putExtra("state", !isEnabled);
		context.sendBroadcast(changeMode);

		if (!isEnabled) {
			views.setInt(R.id.airplane_box, "setBackgroundResource",
					R.color.on_red);
		} else {
			views.setInt(R.id.airplane_box, "setBackgroundResource",
					R.color.on_green);
		}

		Log.d(TAG, " Update remote view: " + views.getLayoutId());

		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		appWidgetManager.updateAppWidget(new ComponentName(context,
				MainWidget.class), views);
	}

}
