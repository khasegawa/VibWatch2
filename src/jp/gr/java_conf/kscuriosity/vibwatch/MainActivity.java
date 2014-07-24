package jp.gr.java_conf.kscuriosity.vibwatch;

import jp.gr.java_conf.kscuriosity.vibwatch.R;

import android.app.Activity;  
import android.content.Intent;  
import android.content.SharedPreferences;  
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import android.widget.CompoundButton;  
import android.widget.CompoundButton.OnCheckedChangeListener;  

public class MainActivity extends Activity {

	public static final String key_enabled = "enabled";
	public static final String key_autostart = "autostart";
	public static final String key_mode = "mode";
	public static final String key_order = "order";
	public static final String key_length = "length";
	public static final String key_interval1 = "interval1";
	public static final String key_interval2 = "interval2";

	public static final int key_mode_def = 1;
	public static final int key_order_def = 2;
	public static final int key_length_def = 60;
	public static final int key_interval1_def = 250;
	public static final int key_interval2_def = 1000;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final SharedPreferences sp =
				getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
		final Intent intent = new Intent(MainActivity.this, VibWatchService.class);

		// VibWatch service on/off
		ToggleButton tglbtnEnabled = (ToggleButton)findViewById(R.id.tglbtn_enabled);
		tglbtnEnabled.setChecked(sp.getBoolean(key_enabled, false));
		tglbtnEnabled.setOnCheckedChangeListener(
			new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (buttonView.getId() == R.id.tglbtn_enabled) {
						sp.edit().putBoolean(key_enabled, isChecked).commit();
						sp.edit().commit();
						if (isChecked) {
							startService(intent);
						} else {
							stopService(intent);
						}
					}
				}
			}
		);
		
		// VibWatch service Power-on start
		CheckBox chkboxAutostart = (CheckBox)findViewById(R.id.chkbox_autostart);
		chkboxAutostart.setChecked(sp.getBoolean(key_autostart, true));
		chkboxAutostart.setOnCheckedChangeListener(
			new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					sp.edit().putBoolean(key_autostart, isChecked).commit();
					sp.edit().commit();
				}
			}
		);

		// Indication mode = 1(always)
		RadioButton radbtnAlways = (RadioButton)findViewById(R.id.radbtn_always);
		radbtnAlways.setChecked(sp.getInt(key_mode, key_mode_def) == 1);
		radbtnAlways.setOnCheckedChangeListener(
			new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						sp.edit().putInt(key_mode, 1).commit();
						sp.edit().commit();
					}
				}
			}
		);

		// Indication mode = 2(ask me)
		RadioButton radbtnAskme = (RadioButton)findViewById(R.id.radbtn_askme);
		radbtnAskme.setChecked(sp.getInt(key_mode, key_mode_def) == 2);
		radbtnAskme.setOnCheckedChangeListener(
			new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						sp.edit().putInt(key_mode, 2).commit();
						sp.edit().commit();
					}
				}
			}
		);

		
		// Vibration order1
		RadioButton radbtnOrder1 = (RadioButton)findViewById(R.id.radbtn_order1);
		radbtnOrder1.setChecked(sp.getInt(key_order, key_order_def) == 1);
		radbtnOrder1.setOnCheckedChangeListener(
			new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						sp.edit().putInt(key_order, 1).commit();
						sp.edit().commit();
					}
				}
			}
		);

		// Vibration order2
		RadioButton radbtnOrder2 = (RadioButton)findViewById(R.id.radbtn_order2);
		radbtnOrder2.setChecked(sp.getInt(key_order, key_order_def) == 2);
		radbtnOrder2.setOnCheckedChangeListener(
			new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						sp.edit().putInt(key_order, 2).commit();
						sp.edit().commit();
					}
				}
			}
		);

		// Vibration order3
		RadioButton radbtnOrder3 = (RadioButton)findViewById(R.id.radbtn_order3);
		radbtnOrder3.setChecked(sp.getInt(key_order, key_order_def) == 3);
		radbtnOrder3.setOnCheckedChangeListener(
			new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						sp.edit().putInt(key_order, 3).commit();
						sp.edit().commit();
					}
				}
			}
		);


		// Vibration length
		EditText edTxtLength = (EditText)findViewById(R.id.edtxt_length);
		edTxtLength.setText(Integer.toString(sp.getInt(key_length, key_length_def)));
		edTxtLength.addTextChangedListener(new TextWatcher(){
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			public void onTextChanged(CharSequence s, int start, int before, int count){}
			@Override
			public void afterTextChanged(Editable s) {
				int length;
				try {
					length = Integer.parseInt(s.toString());
				} catch (NumberFormatException e) {
					length = 0;
				}
				sp.edit().putInt(key_length, length).commit();
				sp.edit().commit();
			}
		}); 

		// Vibration interval1
		EditText edTxtInterval1 = (EditText)findViewById(R.id.edtxt_interval1);
		edTxtInterval1.setText(Integer.toString(sp.getInt(key_interval1, key_interval1_def)));
		edTxtInterval1.addTextChangedListener(new TextWatcher(){
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			public void onTextChanged(CharSequence s, int start, int before, int count){}
			@Override
			public void afterTextChanged(Editable s) {
				int interval1;
				try {
					interval1 = Integer.parseInt(s.toString());
				} catch (NumberFormatException e) {
					interval1 = 0;
				}
				sp.edit().putInt(key_interval1, interval1).commit();
				sp.edit().commit();
			}
		}); 

		// Vibration interval2
		EditText edTxtInterval2 = (EditText)findViewById(R.id.edtxt_interval2);
		edTxtInterval2.setText(Integer.toString(sp.getInt(key_interval2, key_interval2_def)));
		edTxtInterval2.addTextChangedListener(new TextWatcher(){
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			public void onTextChanged(CharSequence s, int start, int before, int count){}
			@Override
			public void afterTextChanged(Editable s) {
				int interval2;
				try {
					interval2 = Integer.parseInt(s.toString());
				} catch (NumberFormatException e) {
					interval2 = 0;
				}
				sp.edit().putInt(key_interval2, interval2).commit();
				sp.edit().commit();
			}
		}); 

	}
}
