package jp.gr.java_conf.kscuriosity.vibwatch;

import jp.gr.java_conf.kscuriosity.vibwatch.R;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.text.format.Time;

public class IndicateTimeService extends Service {
	static PowerManager pm;
	static Vibrator vib;
	static int order, length, interval1, interval2;
	IndicateTime it;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
		it = new IndicateTime();
		
		final SharedPreferences sp = 
				getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
		order = sp.getInt(MainActivity.key_order, MainActivity.key_order_def);
		length = sp.getInt(MainActivity.key_length, MainActivity.key_length_def);
		interval1 = sp.getInt(MainActivity.key_interval1, MainActivity.key_interval1_def);
		interval2 = sp.getInt(MainActivity.key_interval2, MainActivity.key_interval2_def);

    	pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		it.start();
	
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		it.halt();
		it = null;
		super.onDestroy();
	}
}

class IndicateTime extends Thread {

	@Override
	public void start() {
		super.start();
	}

    @Override
	public void run() {
    	Time time = new Time();
    	time.setToNow();
	    	
    	int h =  time.hour % 12 == 0 ? 12 : time.hour % 12;
    	int m10 = time.minute / 10;
    	int m1 =  time.minute % 10;
    	
    	int interval = IndicateTimeService.interval2 - IndicateTimeService.interval1 - IndicateTimeService.length;

    	WakeLock lock = IndicateTimeService.pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "IndicateTime");
    	lock.acquire();
    	
    	try {
    		switch (IndicateTimeService.order) {
    		case 1:
        		vib(h);
    			Thread.sleep(interval);
        		vib(m10);
    			Thread.sleep(interval);
        		vib(m1);
    			break;
    		case 2:
        		vib(m10);
    			Thread.sleep(interval);
        		vib(m1);
    			Thread.sleep(interval);
        		vib(h);
    			break;
   			default:
   	    		vib(m10);
   				Thread.sleep(interval);
   	    		vib(m1);
   				break;
    		}
		} catch (InterruptedException e) {
			/* Do nothing */
		}

    	lock.release();    	
    }
    
    private void vib(int count) throws InterruptedException {
    	int length0 = IndicateTimeService.length * 3;
    	int length = IndicateTimeService.length;
    	int interval = IndicateTimeService.interval1 - IndicateTimeService.length;

    	if (count == 0) {
			VibWatchActivity.vib.vibrate(length0);
		} else {
			for (int i = 0; i < count; i++) {
				VibWatchActivity.vib.vibrate(length);
				Thread.sleep(interval);
			}
		}
    }

    void halt() {
    	interrupt();
    }
}
