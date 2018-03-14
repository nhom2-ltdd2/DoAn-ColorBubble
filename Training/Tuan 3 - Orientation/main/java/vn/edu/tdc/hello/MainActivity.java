package vn.edu.tdc.hello;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bg = (LinearLayout) findViewById(R.id.bg);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            bg.setBackgroundResource(R.drawable.bmoi);
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            bg.setBackgroundResource(R.drawable.amoi);
        }
    }
}
