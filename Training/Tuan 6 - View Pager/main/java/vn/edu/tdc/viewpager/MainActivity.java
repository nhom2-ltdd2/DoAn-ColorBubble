package vn.edu.tdc.viewpager;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    ViewPager myPager;

    private int imageArra[] = { R.mipmap.v,R.mipmap.v2,R.mipmap.v3};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adatapter adapter = new adatapter(this, imageArra);
        myPager = (ViewPager) findViewById(R.id.myfivepanelpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(1);

        final Button a = (Button) findViewById(R.id.bt);
        a.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myPager.setCurrentItem(1);
            }
        });
    }
}
