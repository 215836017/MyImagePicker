package com.fzq.restarapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 重启程序的demo
 * 这里是自己重启自己，有个不足就是：整个重启的过程都是在同一个进程中进行的，当项目复杂一点时，关闭程序的时候可能
 * 会遗漏一些内存没有释放，重启之后会有一些问题。要实现杀死进程重启的话就要通过另外一个进程。
 * 具体见WatchDog(看门狗)的代码。
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
