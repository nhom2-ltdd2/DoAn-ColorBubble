package vn.edu.tdc.nhom2.nhatky.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import vn.edu.tdc.nhom2.nhatky.Models.Preferences;
import vn.edu.tdc.nhom2.nhatky.R;

public class LockActivity extends AppCompatActivity {
    PatternLockView patternLockView;
    Button btn_next;
    String password = "";
    int step = 0;

    PatternLockViewListener patternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {

        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {

        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            switch (step) {
                case 0:
                    password = PatternLockUtils.patternToString(patternLockView, pattern);
                    break;
                case 1:
                    if (password == PatternLockUtils.patternToString(patternLockView, pattern)) {
                        btn_next.setText("HOÀN TẤT");
                    } else {
                        patternLockView.setWrongStateColor(patternLockView.getWrongStateColor());
                    }
                    break;
            }
        }

        @Override
        public void onCleared() {
            password = "";
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patternLockView = findViewById(R.id.pattern_lock_view);
        patternLockView.addPatternLockListener(patternLockViewListener);
        btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test", "Cli");
                switch (step) {
                    case 0:
                        patternLockView.clearPattern();
                        step++;
                        break;
                    case 1:

                        break;
                }
            }
        });
    }
}
