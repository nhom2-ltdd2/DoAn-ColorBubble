package vn.edu.tdc.nhom2.colorbubble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import vn.edu.tdc.nhom2.colorbubble.Model.Preferences;


public class HomeActivity extends AppCompatActivity {

    private Button Start_btn;
    private Button Ranking_btn;
    private Button Setting_btn;
    private Button About_btn;
    private Button Exit_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        Start_btn = (Button) findViewById(R.id.Start_btn);
        Ranking_btn = (Button) findViewById(R.id.Ranking_btn);
        Setting_btn = (Button) findViewById(R.id.Setting_btn);
        About_btn = (Button) findViewById(R.id.About_btn);
        Exit_btn = (Button) findViewById(R.id.Exit_btn);


        Start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean first = Preferences.getQuery(getApplicationContext(), "tutorial");
                if (first) {
                    Intent intent = new Intent(HomeActivity.this, InstructionActivity.class);
                    Preferences.setQuery(getApplicationContext(), "tutorial", false);
                    Preferences.setQueryString(getApplicationContext(), "tutorialtext", "Tutorial Off");
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(HomeActivity.this, WebViewActivity.class);
                    startActivity(intent);
                }

            }


        });
        Ranking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Ranking.class);
                startActivity(intent);
            }


        });
        Setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }


        });
        About_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }


        });

        Exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }


        });

    }
}
