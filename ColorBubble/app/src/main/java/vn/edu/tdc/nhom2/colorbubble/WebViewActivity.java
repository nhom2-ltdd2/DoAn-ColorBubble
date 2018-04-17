package vn.edu.tdc.nhom2.colorbubble;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.game);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsInterface(this), "Android");
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        webView.loadUrl("file:///android_asset/index.html");
        webView.loadUrl("http://192.168.1.18:8080");

    }

    public class JsInterface {
        Context context;
        Intent intent;
        Bundle bundle;
        MediaPlayer mediaPlayer;
        MediaPlayer mpHit;
        MediaPlayer mpStar;
        MediaPlayer mpFinish;

        public JsInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void gameStart() {
            mediaPlayer = MediaPlayer.create(context, R.raw.bg);
            mediaPlayer.setVolume(0.1f,0.1f);
            mediaPlayer.start();
        }

        @JavascriptInterface
        public void gameStar() {
            mpStar = MediaPlayer.create(context, R.raw.star);
            mpStar.start();
        }

        @JavascriptInterface
        public void gameHit() {
            mpHit = MediaPlayer.create(context, R.raw.hit);
            mpHit.start();
        }

        @JavascriptInterface
        public void gameOver(int score, int time) {
            mediaPlayer.pause();
            mpFinish = MediaPlayer.create(context, R.raw.finish);
            mpFinish.start();
            bundle = new Bundle();
            bundle.putInt("Score", score);
            bundle.putInt("Time", time);
            intent = new Intent(context, Gameover.class);
            intent.putExtra("Bundle", bundle);
            context.startActivity(intent);
        }
    }
}
