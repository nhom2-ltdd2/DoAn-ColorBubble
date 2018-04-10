package vn.edu.tdc.nhom2.colorbubble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

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
        webView.addJavascriptInterface(new JsInterface(), "Android");
//        webView.loadUrl("file:///android_asset/index.html");
        webView.loadUrl("http://192.168.1.18:8080");
    }

    class JsInterface extends Object {
        @JavascriptInterface
        public void consoleLog(String log) {
            Log.d("aaa", log);
//            webView.loadUrl("http://192.168.1.18:8080");
        }
    }
}
