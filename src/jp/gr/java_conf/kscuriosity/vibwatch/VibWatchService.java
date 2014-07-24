package jp.gr.java_conf.kscuriosity.vibwatch;

import android.app.Service;
import android.content.BroadcastReceiver;  
import android.content.Intent;
import android.content.IntentFilter;  
import android.os.IBinder;

public class VibWatchService extends Service {
	private BroadcastReceiver scrOnListener = new VibWatchReceiver();
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(scrOnListener, filter);
	}
	
	@Override
	public void onDestroy() {
		unregisterReceiver(scrOnListener);
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
