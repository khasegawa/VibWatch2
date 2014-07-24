package jp.gr.java_conf.kscuriosity.vibwatch;

import jp.gr.java_conf.kscuriosity.vibwatch.R;

import android.app.Activity;  
import android.content.Intent;
import android.content.SharedPreferences;  
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
  
public class StartupModeActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Window win = getWindow();
		win.addFlags(
			WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
			| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
		);
		
		final SharedPreferences sp =
				getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
		
		if (sp.getBoolean(MainActivity.key_autostart, true)) {
			sp.edit().putBoolean(MainActivity.key_enabled, true).commit();
			sp.edit().commit();
			startService(new Intent(StartupModeActivity.this, VibWatchService.class));
		} else {
			sp.edit().putBoolean(MainActivity.key_enabled, false).commit();
			sp.edit().commit();
		}
		finish();
    }
}
