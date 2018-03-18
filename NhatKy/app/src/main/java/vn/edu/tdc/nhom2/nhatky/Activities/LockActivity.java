package vn.edu.tdc.nhom2.nhatky.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import vn.edu.tdc.nhom2.nhatky.Models.Preferences;
import vn.edu.tdc.nhom2.nhatky.R;

public class LockActivity extends AppCompatActivity {
    PatternLockView patternLockView;
    TextView txt_info;
    Button btn_next, btn_prev;
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
                    if (PatternLockUtils.patternToString(patternLockView, pattern).length() > 3) {
                        password = PatternLockUtils.patternToString(patternLockView, pattern);
                        txt_info.setText(R.string.lock_txt_no);
                        btn_next.setEnabled(true);
                    } else {
                        txt_info.setText(R.string.lock_txt_length);
                        patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                    }
                    break;
                case 1:
                    if (password.equals(PatternLockUtils.patternToString(patternLockView, pattern))) {
                        btn_next.setEnabled(true);
                    } else {
                        txt_info.setText(R.string.lock_txt_err);
                        patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                    }
                    break;
                case 2:
                    if (password.equals(PatternLockUtils.patternToString(patternLockView, pattern))) {
                        Log.d("check", "success");
                    } else {
                        txt_info.setText(R.string.lock_txt_ne);
                        patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                    }
                    break;
            }
        }

        @Override
        public void onCleared() {
            switch (step) {
                case 0:
                    password = "";
                    btn_next.setEnabled(false);
                    txt_info.setText(R.string.lock_txt_no);
                    break;
                case 1:
                    btn_next.setEnabled(false);
                    txt_info.setText(R.string.lock_txt_next);
                    break;
                case 2:
                    txt_info.setText(R.string.lock_txt_yes);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patternLockView = findViewById(R.id.pattern_lock_view);
        patternLockView.addPatternLockListener(patternLockViewListener);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);
        txt_info = findViewById(R.id.txt_info);
        String pass = Preferences.getLockCode(this);
        if (!pass.equals("Null")) {
            password = pass;
            txt_info.setText(R.string.lock_txt_yes);
            btn_next.setVisibility(View.INVISIBLE);
            step = 2;

        }

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (step) {
                    case 0:
                        if (!password.equals("")) {
                            patternLockView.clearPattern();
                            txt_info.setText(R.string.lock_txt_next);
                            btn_prev.setVisibility(View.VISIBLE);
                            btn_next.setText(R.string.lock_btn_fin);
                            btn_next.setEnabled(false);
                            step++;
                        }
                        break;
                    case 1:
                        Preferences.setLockCode(LockActivity.this, password);
                        Log.d("check", "cahnge");
                        break;
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                step = 0;
                password = "";
                txt_info.setText(R.string.lock_txt_no);
                btn_next.setText(R.string.lock_btn_next);
                btn_prev.setVisibility(View.INVISIBLE);
                btn_next.setEnabled(false);
                patternLockView.clearPattern();
            }
        });
    }
}
