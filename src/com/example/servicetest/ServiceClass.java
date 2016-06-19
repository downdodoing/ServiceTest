package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServiceClass extends Service {

	private IBinder binder = new LocalBind();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("TAG", "onCreate-----");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i("TAG", "onBind--------");
		return binder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.i("TAG", "onStartCommand-------");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.i("TAG", "onDestroy--------");
	}

	@Override
	public boolean onUnbind(Intent intent) {

		Log.i("TAG", "onUnbind----");
		return super.onUnbind(intent);
	}

	class LocalBind extends Binder {
		String string = "I'm the test string";

		public ServiceClass getService() {
			Log.i("TAG", "getService--->" + ServiceClass.this);
			return ServiceClass.this;
		}
	}

}
