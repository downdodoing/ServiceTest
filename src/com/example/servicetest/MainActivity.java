package com.example.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button startButton, bindButton;
	private Button stopButton, unbindButton;
	private ServiceConnection sc;
	private ServiceClass serviceClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startButton = (Button) findViewById(R.id.button1);
		bindButton = (Button) findViewById(R.id.button2);
		stopButton = (Button) findViewById(R.id.button3);
		unbindButton = (Button) findViewById(R.id.button4);

		sc = new ServiceConnection() {

			// service因异常而断开时才会被调用
			@Override
			public void onServiceDisconnected(ComponentName name) {
				sc = null;
				Log.i("MainActivity", "  ");
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				serviceClass = ((ServiceClass.LocalBind) service).getService();
				String str = ((ServiceClass.LocalBind) service).string;

				Log.i("MainActivity", str);
			}
		};

		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						ServiceClass.class);
				startService(intent);
				Log.i("MainActivity", "startButton");
			}
		});
		bindButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.example.servicetest.ServiceClass");
				bindService(intent, sc, Context.BIND_AUTO_CREATE);
				Log.i("MainActivity", "bindButton");
			}
		});
		stopButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.example.servicetest.ServiceClass");
				stopService(intent);
				Log.i("MainActity", "stopButton");
			}
		});
		unbindButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				unbindService(sc);
				Log.i("MainActity", "unbindButton");
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
