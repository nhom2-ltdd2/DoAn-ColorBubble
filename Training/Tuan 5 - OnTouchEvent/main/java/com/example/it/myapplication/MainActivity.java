package com.example.it.myapplication;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a =(TextView) findViewById(R.id.a);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float downX, downY, upX, upY;
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            Log.d("MotionEvent", "ACTION_DOWN");
            downX = event.getX();
            downY = event.getY();
            String ab = "downX = " + downX;
            Log.d("MotionEvent", "downX = " + downX);
            Log.d("MotionEvent", "downY = " + downY);
            

            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            Log.d("MotionEvent", "ACTlON_UP");
            upX = event.getX();
            upY = event.getY();
            Log.d("MotionEvent", "upX = " + upX);
            Log.d("MotionEvent", "upY = " + upY);
            a.setText("upX = " + upX);

            return true;
        } else
            return false;

    }
}
