package jp.gr.java_conf.kscuriosity.vibwatch;

import jp.gr.java_conf.kscuriosity.vibwatch.R;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;  
import android.view.Window;  
import android.view.WindowManager;  
import android.view.View.OnClickListener;  
import android.widget.Button;  
	  
public class VibWatchActivity extends Activity {
	static public Vibrator vib;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vibwatch);
		
		final Window win = getWindow();
		win.addFlags(
			WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
			| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
		);
		
		final SharedPreferences sp =
				getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
		if (sp.getInt(MainActivity.key_mode, MainActivity.key_mode_def) == 1) {
			vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
			stopService(new Intent(getBaseContext(),IndicateTimeService.class));
			startService(new Intent(getBaseContext(),IndicateTimeService.class));

			finish();
		}

		Button btnTime = (Button)findViewById(R.id.btn_time);
		btnTime.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
					stopService(new Intent(getBaseContext(),IndicateTimeService.class));
					startService(new Intent(getBaseContext(),IndicateTimeService.class));
					
					finish();
				}
			}
		);
		
		Button btnNext = (Button)findViewById(R.id.btn_next);
		btnNext.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			}
		);
	
	}
}
