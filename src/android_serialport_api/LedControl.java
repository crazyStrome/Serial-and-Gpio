package android_serialport_api;

import android.util.Log;

import static android.content.ContentValues.TAG;
/**
 * Created by moyuan on 17-9-29.
 */

public class LedControl {

    private static final String LED_PATH ="/sys/class/leds/red_led/";
    private static final String LED_TRIGGER_PATH =LED_PATH+"trigger";
    private static final String LED_BRIGHTNESS_PATH =LED_PATH+"brightness";
    public static final String LED_TIMER     ="timer";
    public static final String LED_HEARTBEAT ="heartbeat";
    public static final String LED_NONE      ="none";
    public static final String LED_OFF     ="0";
    public static final String LED_ON      ="1";
    static {
        try {
            System.loadLibrary("native_led_control");
        } catch (UnsatisfiedLinkError ule) {
            Log.e(TAG, "UnsatisfiedLinkError library: " + ule);
            System.exit(1);
        } catch (SecurityException se) {
            Log.e(TAG, "SecurityException library: " + se);
            System.exit(1);
        }
    }

    public static native int nativeEnableLed(String path,String value);

    public static boolean enableLedTrigger(String value){
        return  nativeEnableLed(LED_TRIGGER_PATH,value) == 0;
    }
    public static boolean enableLedBrightness(String value){
        return  nativeEnableLed(LED_BRIGHTNESS_PATH,value) == 0;
    }

}
