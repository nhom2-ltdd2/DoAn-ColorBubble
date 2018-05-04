package vn.edu.tdc.hello;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private AudioManager audioManager;

    // Số luồng âm thanh phát ra tối đa.
    private static final int MAX_STREAMS = 5;
    // Chọn loại luồng âm thanh để phát nhạc.
    private static final int streamType = AudioManager.STREAM_MUSIC;

    private boolean loaded = true;

    private int soundDestroy;
    private int soundGun;
    private float volume;

    private Button btndestroy;
    private Button btngun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa
        btndestroy = (Button) findViewById(R.id.btn_destroy);
        btngun = (Button) findViewById(R.id.btn_gun);

        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Chỉ số âm lượng hiện tại của loại luồng nhạc cụ thể (streamType).
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

        // Chỉ số âm lượng tối đa của loại luồng nhạc cụ thể (streamType).
        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);

        // Âm lượng  (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Cho phép thay đổi âm lượng các luồng kiểu 'streamType' bằng các nút
        // điều khiển của phần cứng.
        this.setVolumeControlStream(streamType);

        // Với phiên bản Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // Với phiên bản Android SDK < 21
        else {
            //SoundPool(int maxStreams, int streamType, int srcQuality);
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // Sự kiện SoundPool đã tải lên bộ nhớ thành công.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        // Tải file nhạc tiếng vật thể bị phá hủy (destroy.war) vào SoundPool.
        this.soundDestroy = this.soundPool.load(this, R.raw.destroy,1);

        // Tải file nhạc tiếng súng (gun.wav) vào SoundPool.
        this.soundGun = this.soundPool.load(this, R.raw.gun,1);

        // Khi người dùng nhấn vào button "Gun".
        btngun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               playSoundGun();
            }
        });
        // Khi người dùng nhấn vào button "Destroy".
        btndestroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSoundDestroy();
            }
        });
    }

    // Khi người dùng nhấn vào button "Gun".
    public void playSoundGun()  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Phát âm thanh tiếng súng. Trả về ID của luồng mới phát ra.
            int streamId = this.soundPool.play(this.soundGun,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    // Khi người dùng nhấn vào button "Destroy".
    public void playSoundDestroy()  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;

            // Phát âm thanh tiếng vật thể bị phá hủy.
            // Trả về ID của luồng mới phát ra.
            int streamId = this.soundPool.play(this.soundDestroy,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
}
