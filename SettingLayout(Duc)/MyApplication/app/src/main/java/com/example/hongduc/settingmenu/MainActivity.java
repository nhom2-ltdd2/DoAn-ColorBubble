package com.example.hongduc.settingmenu;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button pause;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

        pause = (Button)findViewById(R.id.btnpause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog() {
        dialog = new Dialog(MainActivity.this,R.style.Theme_CustomDialog);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.pausedialog);
        Button resume = (Button) dialog.findViewById(R.id.btn_resume);
        final Button back = (Button) dialog.findViewById(R.id.btn_back);
        final Button menu = (Button) dialog.findViewById(R.id.btn_menu);
        Button setting = (Button) dialog.findViewById(R.id.btn_setting);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setBackgroundResource(R.drawable.bgresume);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setBackgroundResource(R.drawable.bgexit);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SettingMenu.class));
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
