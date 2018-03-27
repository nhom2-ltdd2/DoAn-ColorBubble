package vn.edu.tdc.nhom2.colorbubble;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class Gameover extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.gameover);

    }
}
