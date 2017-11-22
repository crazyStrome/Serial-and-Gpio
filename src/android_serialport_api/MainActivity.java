package android_serialport_api;

import android.app.Activity;
import android.os.Bundle;
import android.os.Gpio;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android_serialport_api.sample.R;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
/*	TextView mGetResult;
	EditText mCharText;
	EditText mIntegerText;
	Button mReadButton;
	Button mWriteButton;*/
	Button mLedTimerButton;
	Button mLedHeartbeatButton;
	Button mLedOnButton;
	Button mLedOffButton;
	Button mPB2set1Button;
	Button mPB2set0Button;
	Button mPB3set1Button;
	Button mPB3set0Button;

	char mGpioChar    ='b';
	int  mGpioInteger = 0;
	boolean toggled = false;
	boolean timered = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
/*		mGetResult    = (TextView) findViewById(R.id.tv_result);
		mCharText     = (EditText) findViewById(R.id.et_H);
		mIntegerText  = (EditText) findViewById(R.id.et_endpoit);
		mReadButton   = (Button) findViewById(R.id.bt_read);
		mWriteButton  = (Button) findViewById(R.id.bt_write);*/
		mLedTimerButton        = (Button) findViewById(R.id.bt_led_timer);
		mLedHeartbeatButton    = (Button) findViewById(R.id.bt_led_heartbeat);
		mLedOnButton         = (Button) findViewById(R.id.bt_led_on);
		mLedOffButton         = (Button) findViewById(R.id.bt_led_off);
		mPB2set1Button          = (Button) findViewById(R.id.bt_pb2_set_1);
		mPB2set0Button          = (Button) findViewById(R.id.bt_pb2_set_0);
		mPB3set1Button          = (Button) findViewById(R.id.bt_pb3_set_1);
		mPB3set0Button          = (Button) findViewById(R.id.bt_pb3_set_0);

		//mReadButton.setOnClickListener(mButtonListener);
		//mWriteButton.setOnClickListener(mButtonListener);
		mLedTimerButton.setOnClickListener(mButtonListener);
		mLedHeartbeatButton.setOnClickListener(mButtonListener);
		mLedOnButton.setOnClickListener(mButtonListener);
		mLedOffButton.setOnClickListener(mButtonListener);
		mPB2set1Button.setOnClickListener(mButtonListener);
		mPB2set0Button.setOnClickListener(mButtonListener);
		mPB3set1Button.setOnClickListener(mButtonListener);
		mPB3set0Button.setOnClickListener(mButtonListener);
	}


	private MyClickListener mButtonListener = new MyClickListener();
	class MyClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()){
	/*			case R.id.bt_read:
				{
					Log.d(TAG,"readGpio mGpioChar: "+mGpioChar +"mGpioInteger : "+mGpioInteger);
					int i =Gpio.readGpio(mGpioChar,mGpioInteger);
					//mGetResult.setText(""+i);
				}
					break;
				case R.id.bt_write:
				{
					char tmp = mCharText.getText().toString().charAt(0);
					if(tmp !='\0'){
						mGpioChar = tmp;
					}
					mGpioInteger = Integer.parseInt(mIntegerText.getText().toString());
					Log.d(TAG,"writeGpio mGpioChar: "+mGpioChar +"mGpioInteger : "+mGpioInteger);
					Gpio.writeGpio(mGpioChar,mGpioInteger,toggled?1:0);
					toggled = !toggled;
				}
					break;*/
				case R.id.bt_led_timer:
				{
					LedControl.enableLedTrigger(LedControl.LED_TIMER);
				}
				break;
				case R.id.bt_led_heartbeat:
				{
					LedControl.enableLedTrigger(LedControl.LED_HEARTBEAT);
				}
				break;
				case R.id.bt_led_on:
				{
					LedControl.enableLedTrigger(LedControl.LED_NONE);
					LedControl.enableLedBrightness(LedControl.LED_ON);
				}
				break;
				case R.id.bt_led_off:
				{
					LedControl.enableLedTrigger(LedControl.LED_NONE);
					LedControl.enableLedBrightness(LedControl.LED_OFF);
				}
				break;
				case R.id.bt_pb2_set_1:
				{
					Gpio.writeGpio(mGpioChar,2,1);
				}
				break;
				case R.id.bt_pb2_set_0:
				{
					Gpio.writeGpio(mGpioChar,2,0);
				}
				break;
				case R.id.bt_pb3_set_1:
				{
					Gpio.writeGpio(mGpioChar,3,1);
				}
				break;
				case R.id.bt_pb3_set_0:
				{
					Gpio.writeGpio(mGpioChar,3,0);
				}
				break;

				default:
					Log.d(TAG,"no match button");
			}
		}
	}
}
