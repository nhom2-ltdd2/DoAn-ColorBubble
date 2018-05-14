package vn.edu.tdc.nhom2.colorbubble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import vn.edu.tdc.nhom2.colorbubble.Model.Database;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;

public class SplashActivity extends AppCompatActivity {

    protected boolean _active = true;
    protected int _splashTime = 300;
    ArrayList<Score> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Database data = new Database(getApplicationContext());
        arr = data.getAllScore();
        if (arr.size() == 0) {
            data.addScore(new Score("Ravi", 10, 60,"a"));
            data.addScore(new Score("Vinh", 9, 60,"a"));
            data.addScore(new Score("Truong", 8, 60,"a"));
            data.addScore(new Score("Tien", 7, 60,"a"));
            data.addScore(new Score("Dat", 6, 60,"a"));
            data.addScore(new Score("Khanh", 5, 60,"a"));
            data.addScore(new Score("Tai", 4, 60,"a"));
            data.addScore(new Score("Tuong", 3, 60,"a"));
            data.addScore(new Score("Hau", 2, 60,"a"));
            data.addScore(new Score("Hoa", 1, 60,"a"));

        }

        /** Called when the activity is first created. */
        // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }

                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);

                }
            }
        };
        splashTread.start();

    }
}
