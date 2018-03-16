package vn.edu.tdc.nhom2.nhatky.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;

import java.util.List;

import vn.edu.tdc.nhom2.nhatky.R;

public class LockActivity extends AppCompatActivity {
    PatternLockView patternLockView;
    PatternLockViewListener patternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {

        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {

        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {

        }

        @Override
        public void onCleared() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patternLockView = findViewById(R.id.pattern_lock_view);
        patternLockView.addPatternLockListener(patternLockViewListener);
    }
}
