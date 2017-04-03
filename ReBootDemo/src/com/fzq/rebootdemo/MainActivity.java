package com.fzq.rebootdemo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

/**
 * 一个实现关机/重启 系统（设备）的demo
 * 注意：需要root设备
 * @author fzq
 * 
 */
public class MainActivity extends Activity {

	private Button btnReboot, btnShutdown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		btnReboot = (Button) findViewById(R.id.btnReboot);
		btnShutdown = (Button) findViewById(R.id.btnShutdown);

		btnReboot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				reboot();
			}
		});

		btnShutdown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shutdownn();
			}
		});

	}

	private String TAG = "rebootDemo";

	protected void reboot() {
		try {
			Log.v(TAG, "root Runtime->shutdown");
			// Process proc =Runtime.getRuntime().exec(new
			// String[]{"su","-c","shutdown"}); //关机
			Process proc = Runtime.getRuntime().exec(
					new String[] { "su", "-c", "reboot -p" }); // 重启
			proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void shutdownn() {
		try {
			Log.v(TAG, "root Runtime->shutdown");
			Process proc =Runtime.getRuntime().exec(new
			String[]{"su","-c","shutdown"}); //关机
			//Process proc = Runtime.getRuntime().exec(
			//		new String[] { "su", "-c", "reboot -p" }); // 重启
			proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
