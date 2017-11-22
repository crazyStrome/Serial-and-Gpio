/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package android_serialport_api.sample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;

import android_serialport_api.SerialPort;

public abstract class SerialPortActivity extends Activity {

	private String mTty2;
	private String mTty3;
	private int baudrate = 115200;
	protected SerialPort mTty2SerialPort, mTty3SerialPort;
	protected OutputStream mtty2OutputStream, mTty3OutputStream;
	private InputStream mTty2InputStream, mTty3InputStream;
	private ReadTty2Thread mTty2ReadThread;
	private ReadTty3Thread mTty3ReadThread;

	private class ReadTty2Thread extends Thread {

		@Override
		public void run() {
			super.run();
			while(!isInterrupted()) {
				int size;
				try {
					byte[] buffer = new byte[64];
					if (mTty2InputStream == null) return;
					size = mTty2InputStream.read(buffer);
					if (size > 0) {
						onTty2DataReceived(buffer, size);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
	private class ReadTty3Thread extends Thread {

		@Override
		public void run() {
			super.run();
			while(!isInterrupted()) {
				int size;
				try {
					byte[] buffer = new byte[64];
					if (mTty3InputStream == null) return;
					size = mTty3InputStream.read(buffer);
					if (size > 0) {
						onTty3DataReceived(buffer, size);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	private void DisplayError(int resourceId) {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Error");
		b.setMessage(resourceId);
		b.setPositiveButton("OK", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				SerialPortActivity.this.finish();
			}
		});
		b.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTty2 = "\\dev\\ttyS2";
		mTty3 = "\\dev\\ttyS3";
		try {
			mTty2SerialPort = new SerialPort(new File(mTty2), baudrate, 0);
			mTty2InputStream = mTty2SerialPort.getInputStream();
			mtty2OutputStream = mTty2SerialPort.getOutputStream();

			mTty2ReadThread = new ReadTty2Thread();
			mTty2ReadThread.start();


			mTty3SerialPort = new SerialPort(new File(mTty3), baudrate, 0);
			mTty3InputStream = mTty3SerialPort.getInputStream();
			mTty3OutputStream = mTty3SerialPort.getOutputStream();

			mTty3ReadThread = new ReadTty3Thread();
			mTty3ReadThread.start();
		} catch (SecurityException e) {
			DisplayError(R.string.error_security);
		} catch (IOException e) {
			DisplayError(R.string.error_unknown);
		} catch (InvalidParameterException e) {
			DisplayError(R.string.error_configuration);
		}
		Log.d("serialport", "create");
	}

	protected abstract void onTty2DataReceived(final byte[] buffer, final int size);
	protected abstract void onTty3DataReceived(final byte[] buffer, final int size);

	@Override
	protected void onDestroy() {
		if (mTty2ReadThread != null)
			mTty2ReadThread.interrupt();
		if (mTty3ReadThread != null)
			mTty3ReadThread.interrupt();
		if (mTty2SerialPort != null) {
			mTty2SerialPort.close();
			mTty2SerialPort = null;
		}
		if (mTty3SerialPort != null) {
			mTty3SerialPort.close();
			mTty3SerialPort = null;
		}
		super.onDestroy();
		Log.d("serialport", "destroy");
	}
}
