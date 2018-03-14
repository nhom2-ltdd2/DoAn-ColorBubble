package vn.edu.tdc.mp3;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class SelectorActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageButton btnPlay, btnPause, btnStop, btnNext, btnBack;
    int numberAudio, length;
    boolean isPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        numberAudio = 1;
        // Khởi tạo Mediaplayer và các button
        mediaPlayer = MediaPlayer.create(this, R.raw.b1);
        btnPlay = (ImageButton) findViewById(R.id.btnplay);
        btnPause = (ImageButton) findViewById(R.id.btnpause);
        btnStop = (ImageButton) findViewById(R.id.btnstop);
        btnNext = (ImageButton) findViewById(R.id.btnnext);
        btnBack = (ImageButton) findViewById(R.id.btnprev);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạm dừng mediaplayer
                mediaPlayer.pause();
                //Lấy ra thời gian tạm dừng
                length = mediaPlayer.getCurrentPosition();
                //set biến tạm dừng là "true"
                isPause = true;
                //Thông báo
                Toast.makeText(SelectorActivity.this, "Pausing", Toast.LENGTH_SHORT).show();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dừng hẳn mediaplayer
                mediaPlayer.stop();
                //Thông báo
                Toast.makeText(SelectorActivity.this, "Stoped", Toast.LENGTH_SHORT).show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) // Nếu mediaplayer đã tồn tại thì giải phóng
                    mediaPlayer.release();

                // Next các Audio mới bằng cách tạo mới và play
                // Lưu ý đây chỉ là cách đơn giản, nếu muốn play 1 danh sách thì sẽ làm theo cách khác
                if (numberAudio == 2) {
                    numberAudio = 1;
                    mediaPlayer = MediaPlayer.create(SelectorActivity.this, R.raw.b1);

                } else if (numberAudio == 1) {
                    numberAudio = 2;
                    mediaPlayer = MediaPlayer.create(SelectorActivity.this, R.raw.b2);
                }
                mediaPlayer.start();
                Toast.makeText(SelectorActivity.this, "Next", Toast.LENGTH_SHORT).show();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tương tự next
                if (mediaPlayer != null)
                    mediaPlayer.release();

                if (numberAudio == 2) {
                    numberAudio = 1;
                    mediaPlayer = MediaPlayer.create(SelectorActivity.this, R.raw.b1);

                } else if (numberAudio == 1) {
                    numberAudio = 2;
                    mediaPlayer = MediaPlayer.create(SelectorActivity.this, R.raw.b2);
                }
                mediaPlayer.start();
                Toast.makeText(SelectorActivity.this, "Back", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void playMusic(){
        if (isPause) {//Nếu media đang pause thì
            //set biến playing = false
            isPause = false;
            //Nhảy đến vị trí tạm dừng
            mediaPlayer.seekTo(length);
            //Play lại
            mediaPlayer.start();
            //Thông báo
            Toast.makeText(SelectorActivity.this, "Resume", Toast.LENGTH_SHORT).show();
        } else {//Nếu hàm không phải đang tạm dừng
            if (mediaPlayer.isPlaying()) {//Nếu media là đang chạy
                //thông báo là đang chạy
                Toast.makeText(SelectorActivity.this, "isPlaying", Toast.LENGTH_SHORT).show();
            } else { //nếu media không phải đang tạm dừng
                //Giải phóng
                mediaPlayer.release();
                //Và tạo mới media và start
                if (numberAudio == 1) {
                    mediaPlayer = MediaPlayer.create(SelectorActivity.this, R.raw.b1);
                } else if (numberAudio == 2) {
                    mediaPlayer = MediaPlayer.create(SelectorActivity.this, R.raw.b2);
                }
                mediaPlayer.start();
                //thông báo
                Toast.makeText(SelectorActivity.this, "Playing", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
