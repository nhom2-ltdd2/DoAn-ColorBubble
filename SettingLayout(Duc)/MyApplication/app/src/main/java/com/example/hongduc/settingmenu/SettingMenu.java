package com.example.hongduc.settingmenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dd.CircularProgressButton;

public class SettingMenu extends AppCompatActivity {
    ToggleButton tbtnsound,tbtnmusic,tbtntutorial;
    TextView edtsound,edtmusic,edttutorial;
    CircularProgressButton cpbApply, cpbHTP,cpbAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);

        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

        cpbApply = (CircularProgressButton) findViewById(R.id.cpbApply);
        cpbHTP = (CircularProgressButton) findViewById(R.id.cpbhowtp);
        cpbAbout = (CircularProgressButton) findViewById(R.id.cpbAboutus);
        tbtnsound = (ToggleButton) findViewById(R.id.btn_sound);
        tbtnmusic = (ToggleButton) findViewById(R.id.btn_music);
        tbtntutorial = (ToggleButton) findViewById(R.id.btn_tutorial);
        edtsound = (TextView) findViewById(R.id.edt_sound);
        edtmusic = (TextView) findViewById(R.id.edt_music);
        edttutorial = (TextView) findViewById(R.id.edt_tutorial);

        cpbApply.setIndeterminateProgressMode(true);
        tbtnsound.setChecked(true);
        tbtnmusic.setChecked(true);
        tbtntutorial.setChecked(true);
        soundClick();
        musicClick();
        tutorialClick();
        cirClick();
    }

    public void cirClick()
    {
        cpbApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cpbApply.getProgress()==0)
                {
                    cpbApply.setProgress(30);
                }else if (cpbApply.getProgress() == -1)
                {
                    cpbApply.setProgress(0);
                }else if (cpbApply.getProgress()==100)
                {
                    startActivity(new Intent(SettingMenu.this,MainActivity.class));
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cpbApply.setProgress(100);
                    }
                },2500);
            }
        });

        cpbHTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                htpDia();
            }
        });

        cpbAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutdia();
            }
        });
    }
    private void aboutdia()
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("About Us");
        alert.setIcon(R.drawable.bongbong);
        alert.setMessage("Nhóm 2: Game Buble\nGroup members:Đức,Vinh,Tiến,....\nVersion: 1.0.0\nLast update: 27/3/2018");
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

    private void htpDia()
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Hướng dẫn chơi!");
        alert.setIcon(R.mipmap.ic_launcher);
        alert.setMessage("ALT + F4 để win =))");
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

    public void soundClick()
    {
       tbtnsound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b)
               {
                   edtsound.setText("Sound On");
               }else
               {
                   edtsound.setText("Sound Off");
               }
           }
       });
    }

    public void musicClick()
    {
        tbtnmusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    edtmusic.setText("Music On");
                }else
                {
                    edtmusic.setText("Music Off");
                }
            }
        });
    }

    public void tutorialClick()
    {
        tbtntutorial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    edttutorial.setText("Tutorial On");
                }else
                {
                    edttutorial.setText("Tutorial Off");
                }
            }
        });
    }
}
