package android_serialport_api;

import android.app.Activity;
import android.os.Bundle;

import android_serialport_api.sample.R;

/**
 * Created by HSP on 2017/11/19.
 */

public class SerialAndGpioMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serial_and_gpio_activity_main);
    }
}
