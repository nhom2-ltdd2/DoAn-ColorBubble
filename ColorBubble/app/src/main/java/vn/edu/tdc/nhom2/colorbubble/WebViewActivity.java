package vn.edu.tdc.nhom2.colorbubble;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import vn.edu.tdc.nhom2.colorbubble.Model.Preferences;

public class WebViewActivity extends AppCompatActivity implements SensorListener {

    private SensorManager sensorMgr;
    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 800;

    WebView webView;

    public static Bitmap getScreenshot() {
        return screenshot;
    }

    public static void setScreenshot(Bitmap screenshot) {
        WebViewActivity.screenshot = screenshot;
    }

    public static Bitmap screenshot;

    boolean dataSound;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_view);

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        boolean accelSupported = sensorMgr.registerListener(this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);

        if (!accelSupported) {
            // on accelerometer on this device
            sensorMgr.unregisterListener(this,
                    SensorManager.SENSOR_ACCELEROMETER);
        }

        dataSound = Preferences.getQuery(getApplicationContext(), "sound");
        webView = findViewById(R.id.game);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsInterface(this), "Android");
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (dataSound) {
            webView.loadUrl("file:///android_asset/index.html?sound=true");
        } else {
            webView.loadUrl("file:///android_asset/index.html?sound=false");
        }
//        webView.loadUrl("http://192.168.1.19:8080");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.pause();
        webView.destroy();
    }
    protected void onPause() {
        if (sensorMgr != null) {
            sensorMgr.unregisterListener(this,
                    SensorManager.SENSOR_ACCELEROMETER);
            sensorMgr = null;
        }
        super.onPause();
    }

    public void onAccuracyChanged(int arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    public void onSensorChanged(int sensor, float[] values) {
        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                if(Round(x,4)>10.0000){
                    View view = getWindow().getDecorView().findViewById(android.R.id.content);
                    int[] coordinates = new int[2];
                    view.getLocationOnScreen(coordinates);

                    long downTime = SystemClock.uptimeMillis();
                    long eventTime = SystemClock.uptimeMillis() + 100;
                    float x = 936.0f;
                    float y = 2539.0f;

                    int metaState = 0;
                    MotionEvent motionEvent = MotionEvent.obtain(
                            downTime,
                            eventTime,
                            MotionEvent.ACTION_DOWN,
                            x,
                            y,
                            metaState
                    );
// Dispatch touch event to view
                    view.dispatchTouchEvent(motionEvent);
                    motionEvent = MotionEvent.obtain(
                            downTime,
                            eventTime,
                            MotionEvent.ACTION_UP,
                            x,
                            y,
                            metaState
                    );
                    view.dispatchTouchEvent(motionEvent);


                }
                else if(Round(x,4)<-10.0000){

                    View view = getWindow().getDecorView().findViewById(android.R.id.content);
                    int[] coordinates1 = new int[2];
                    view.getLocationOnScreen(coordinates1);
                    // Obtain MotionEvent object
                    long downTime = SystemClock.uptimeMillis();
                    long eventTime = SystemClock.uptimeMillis() + 100;
                    float x = 1274.0f;
                    float y = 2539.0f;
// List of meta states found here:     developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
                    int metaState = 0;
                    MotionEvent motionEvent = MotionEvent.obtain(
                            downTime,
                            eventTime,
                            MotionEvent.ACTION_DOWN,
                            x,
                            y,
                            metaState
                    );
// Dispatch touch event to view
                    view.dispatchTouchEvent(motionEvent);
                    motionEvent = MotionEvent.obtain(
                            downTime,
                            eventTime,
                            MotionEvent.ACTION_UP,
                            x,
                            y,
                            metaState
                    );
                    view.dispatchTouchEvent(motionEvent);


                }

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                // Log.d("sensor", "diff: " + diffTime + " - speed: " + speed);
                if (speed > SHAKE_THRESHOLD) {
                    //Log.d("sensor", "shake detected w/ speed: " + speed);
                    //Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    public static float Round(float Rval, int Rpl) {
        float p = (float)Math.pow(10,Rpl);
        Rval = Rval * p;
        float tmp = Math.round(Rval);
        return (float)tmp/p;
    }

    public class JsInterface {
        Context context;
        Intent intent;
        Bundle bundle;

        MediaPlayer mpHit;
        MediaPlayer mpStar;
        MediaPlayer mpFinish;
        boolean sound = Preferences.getQuery(getApplicationContext(), "sound");
        final View view = getWindow().getDecorView().findViewById(android.R.id.content);

        public JsInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void gameStart() {
            mediaPlayer = MediaPlayer.create(context, R.raw.bg);
            mediaPlayer.setVolume(0.1f, 0.1f);
            if (sound) {
                mediaPlayer.start();
            }

        }

        @JavascriptInterface
        public void gamePause() {
            mediaPlayer.pause();
        }

        @JavascriptInterface
        public void gameConti() {
            if (sound) {
                mediaPlayer.start();
            }
        }

        @JavascriptInterface
        public void gameStar() {
            mpStar = MediaPlayer.create(context, R.raw.star);
            mpStar.setVolume(0.5f, 0.5f);
            if (sound) {
                mpStar.start();
            }
        }

        @JavascriptInterface
        public void gameHit() {
            mpHit = MediaPlayer.create(context, R.raw.hit);
            mpHit.setVolume(0.5f, 0.5f);
            if (sound) {
                mpHit.start();
            }
        }

        @JavascriptInterface
        public void gameOver(int score, int time) {
            mediaPlayer.pause();
            mpFinish = MediaPlayer.create(context, R.raw.finish);
            if (sound) {
                mpFinish.start();
            }
            setScreenshot(screenShot(view));

//            String hinh = convertToBase64(screenShot(view));

            bundle = new Bundle();
            bundle.putInt("Score", score);
            bundle.putInt("Time", time);

            Log.d("a", bundle.getInt("Score") + "");
            intent = new Intent(context, Gameover.class);
            intent.putExtra("Bundle", bundle);
            context.startActivity(intent);

        }


        @JavascriptInterface
        public void toggleSound() {
            sound = !sound;
            Preferences.setQuery(getApplicationContext(), "sound", sound);
            Log.d("test1", String.valueOf(sound));
            if (sound) {
                Preferences.setQueryString(getApplicationContext(), "soundtext", "Sound On");
                mediaPlayer.start();
            } else {
                Preferences.setQueryString(getApplicationContext(), "soundtext", "Sound Off");
                mediaPlayer.pause();
            }
        }

    }

    public Bitmap screenShot(View view) {

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }


}
