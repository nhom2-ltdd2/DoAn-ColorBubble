package vn.edu.tdc.nhom2.colorbubble;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingActivity extends AppCompatActivity {
    ToggleButton tbtnsound, tbtnmusic, tbtntutorial;
    TextView edtsound, edtmusic, edttutorial, edtvolume;
    Button cpbApply, cpbHTP, cpbAbout;
    SeekBar volumebar;

    LinearLayout mylayout;
    AnimationDrawable animationDrawable;

    int progress = 75;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //hiệu ứng khi chuyển sang activity mới
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

        //ánh xạ
        cpbApply = (Button) findViewById(R.id.cpbApply);
        cpbHTP = (Button) findViewById(R.id.cpbhowtp);
        cpbAbout = (Button) findViewById(R.id.cpbAboutus);
        tbtnsound = (ToggleButton) findViewById(R.id.btn_sound);
        tbtnmusic = (ToggleButton) findViewById(R.id.btn_music);
        tbtntutorial = (ToggleButton) findViewById(R.id.btn_tutorial);
        edtsound = (TextView) findViewById(R.id.edt_sound);
        edtmusic = (TextView) findViewById(R.id.edt_music);
        edttutorial = (TextView) findViewById(R.id.edt_tutorial);
        volumebar = (SeekBar) findViewById(R.id.volume_bar);
        edtvolume = (TextView) findViewById(R.id.volume_number);

        //Mặc định là ON khi được mở
        tbtnsound.setChecked(true);
        tbtnmusic.setChecked(true);
        tbtntutorial.setChecked(true);

        //khai báo các hàm xử lý
        soundClick();
        musicClick();
        tutorialClick();
        cirClick();
        volumebarClick();
    }
    //Hàm xử lý nút apply (đóng activity setting sau khi ấn)
    public void volumebarClick()
    {
        volumebar.setMax(100);
        volumebar.setProgress(progress);
        edtvolume.setText(""+progress+"%");
        volumebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                edtvolume.setText(""+progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void cirClick()
    {
        cpbApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,MainActivity.class));
            }
        });

        cpbHTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                htpDia(R.style.DialogSlideup);
            }
        });

        cpbAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutdia(R.style.DialogSlideleft);
            }
        });
    }

    //hàm xuất dialog của nút about us(thông tin của nhóm)
    private void aboutdia(int type)
    {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("About Us");
        alert.setIcon(R.drawable.bongbong);
        alert.setMessage("Nhóm 2: Game Bubble\nGroup members:Đức,Vinh,Tiến,Trường,Lai,Phước\nGVHD: Thầy Trương Bá Thái\nVersion: 1.0.2\nLast update: 29/3/2018");
        alert.getWindow().getAttributes().windowAnimations  = type;
        alert.show();
    }

    //hàm xuất dialog của nút htp
    private void htpDia(int type)
    {
        final AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Hướng dẫn chơi!");
        alert.setIcon(R.drawable.bongbong);
        alert.setMessage("Luật chơi:\n -");
        alert.getWindow().getAttributes().windowAnimations  = type;
        alert.show();
    }

    //hàm xử lý nút turn on - turn off sau khi ấn.
    public void soundClick()
    {
        tbtnsound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    edtsound.setText("Sound On");
                    edtsound.setTextColor(getResources().getColor(R.color.blue));
                }else
                {
                    edtsound.setText("Sound Off");
                    edtsound.setTextColor(getResources().getColor(R.color.gray2));
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
                    edtmusic.setTextColor(getResources().getColor(R.color.blue));
                }else
                {
                    edtmusic.setText("Music Off");
                    edtmusic.setTextColor(getResources().getColor(R.color.gray2));
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
                    edttutorial.setTextColor(getResources().getColor(R.color.blue));
                }else
                {
                    edttutorial.setText("Tutorial Off");
                    edttutorial.setTextColor(getResources().getColor(R.color.gray2));
                }
            }
        });
    }
}
