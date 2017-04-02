package com.fzq.restarapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * ���������demo
 * �������Լ������Լ����и�������ǣ����������Ĺ��̶�����ͬһ�������н��еģ�����Ŀ����һ��ʱ���رճ����ʱ�����
 * ����©һЩ�ڴ�û���ͷţ�����֮�����һЩ���⡣Ҫʵ��ɱ�����������Ļ���Ҫͨ������һ�����̡�
 * �����WatchDog(���Ź�)�Ĵ��롣
 * @author fzq
 *
 */
public class MainActivity extends Activity {

	private Button btnRestart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		btnRestart = (Button) findViewById(R.id.btn_reStart);
		
		btnRestart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				restartApp();
			}
		});
		
	}

	protected void restartApp() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Intent intent = getBaseContext().getPackageManager()
				.getLaunchIntentForPackage(getBaseContext().getPackageName());
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
