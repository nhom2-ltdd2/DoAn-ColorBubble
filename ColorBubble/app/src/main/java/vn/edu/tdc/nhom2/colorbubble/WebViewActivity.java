package vn.edu.tdc.nhom2.colorbubble;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import vn.edu.tdc.nhom2.colorbubble.Model.Preferences;

public class WebViewActivity extends AppCompatActivity {

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
