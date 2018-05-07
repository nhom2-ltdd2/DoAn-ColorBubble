package com.example.hongduc.mymediaplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txttile,txttimesong,txttimetota;
    SeekBar sbsong;
    ImageView imghinh;
    ImageButton btnprev,btnplay,btnstop,btnnext;

    Animation animation;

    MediaPlayer mediaPlayer;
    ArrayList<Song> arraySong;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        AddSong();
        animation = AnimationUtils.loadAnimation(this,R.anim.disc_rotate);
        khoitaomediaPlayer();

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arraySong.size() - 1)
                {
                    position = 0;
                }
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                khoitaomediaPlayer();
                mediaPlayer.start();
                btnplay.setImageResource(R.mipmap.pauseicon);
                setTimetotal();
                updateTimeSong();
            }
        });
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0 )
                {
                    position = arraySong.size() - 1;
                }
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                khoitaomediaPlayer();
                mediaPlayer.start();
                btnplay.setImageResource(R.mipmap.pauseicon);
                setTimetotal();
                updateTimeSong();
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                btnplay.setImageResource(R.mipmap.playicon);
                khoitaomediaPlayer();
            }
        });

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                {
                    //nếu đang play -> pause
                    mediaPlayer.pause();
                    btnplay.setImageResource(R.mipmap.pauseicon);
                }else
                {
                    //nếu đang ngừng -> play
                    mediaPlayer.start();
                    btnplay.setImageResource(R.mipmap.playicon);
                }
                setTimetotal();
                updateTimeSong();
                imghinh.startAnimation(animation);
            }
        });

        sbsong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbsong.getProgress());
            }
        });
    }

    private void khoitaomediaPlayer()
    {
        mediaPlayer = MediaPlayer.create(MainActivity.this,arraySong.get(position).getFile());
        txttile.setText(arraySong.get(position).getTitle());
    }

    private void AddSong()
    {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Fly Away",R.raw.flyaway));
        arraySong.add(new Song("Summer time",R.raw.summertime));
    }

    private void updateTimeSong()
    {
        final Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangtime = new SimpleDateFormat("mm:ss");
                txttimesong.setText(dinhDangtime.format(mediaPlayer.getCurrentPosition()));
                //update progress sbsong
                sbsong.setProgress(mediaPlayer.getCurrentPosition());
                //kiểm  tra nếu hết tg bài hát -> nếu kết thúc -> next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arraySong.size() - 1)
                        {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying())
                        {
                            mediaPlayer.stop();
                        }
                        khoitaomediaPlayer();
                        mediaPlayer.start();
                        btnplay.setImageResource(R.mipmap.pauseicon);
                        setTimetotal();
                        updateTimeSong();
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);
    }

    private void setTimetotal()
        {
        SimpleDateFormat dinhdangtime = new SimpleDateFormat("mm:ss");
        txttimetota.setText(dinhdangtime.format(mediaPlayer.getDuration()));
        //gán max của seekbar  = mediaplay.duration()
        sbsong.setMax(mediaPlayer.getDuration());
    }

    private void anhxa()
    {
        txttile = (TextView) findViewById(R.id.txttitle);
        txttimesong = (TextView) findViewById(R.id.txttimerun);
        txttimetota = (TextView) findViewById(R.id.txttimetotal);
        sbsong = (SeekBar) findViewById(R.id.sbtime);
        btnprev = (ImageButton) findViewById(R.id.btnbackward);
        btnplay = (ImageButton) findViewById(R.id.btnplay);
        btnstop = (ImageButton) findViewById(R.id.btnpause);
        btnnext = (ImageButton) findViewById(R.id.btnnext);
        imghinh = (ImageView) findViewById(R.id.imghinh);
    }
}
