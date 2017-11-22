package android_serialport_api;

import android.app.Activity;
import android.os.Bundle;

import android_serialport_api.sample.R;
import android_serialport_api.sample.SerialPortActivity;

/**
 * Created by HSP on 2017/11/19.
 */

public class SerialAndGpioMainActivity extends SerialPortActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serial_and_gpio_activity_main);
    }

    @Override
    protected void onTty2DataReceived(byte[] buffer, int size) {
    }

    @Override
    protected void onTty3DataReceived(byte[] buffer, int size) {
    }
}
