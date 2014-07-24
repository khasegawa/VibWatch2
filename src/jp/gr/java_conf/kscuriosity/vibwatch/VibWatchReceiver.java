package jp.gr.java_conf.kscuriosity.vibwatch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VibWatchReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i;
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
			i = new Intent(context, StartupModeActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		} else if (action.equals(Intent.ACTION_SCREEN_ON)) {
			i = new Intent(context, VibWatchActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
	}
}
