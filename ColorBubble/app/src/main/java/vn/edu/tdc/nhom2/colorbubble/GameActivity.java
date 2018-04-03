package vn.edu.tdc.nhom2.colorbubble;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;

import com.e3roid.E3Activity;
import com.e3roid.E3Engine;
import com.e3roid.E3Scene;
import com.e3roid.drawable.Sprite;
import com.e3roid.drawable.texture.AssetTexture;
import com.e3roid.drawable.texture.Texture;

public class GameActivity extends E3Activity {
    private final static int WIDTH = 480;
    private int wi;
    private final static int HEIGHT = 320;
    private Texture texture, tt_bg;
    private Sprite bong, s_bg;

    public GameActivity() {
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        wi = metrics.heightPixels;
        Log.d("aaa", String.valueOf(wi));
    }

    @Override
    public E3Engine onLoadEngine() {
        E3Engine engine = new E3Engine(this, WIDTH, HEIGHT);
        engine.requestFullScreen();
        engine.requestPortrait();
        return engine;
    }

    @Override
    public E3Scene onLoadScene() {
        E3Scene scene = new E3Scene();
        s_bg = new Sprite(tt_bg,0,0);
        bong = new Sprite(texture, 0, 0);
        scene.getTopLayer().add(s_bg);
        scene.getTopLayer().add(bong);
        return scene;
    }

    @Override
    public void onLoadResources() {
        tt_bg = new AssetTexture("background.jpg", this);
        texture = new AssetTexture("b1.png", this);
    }
}
