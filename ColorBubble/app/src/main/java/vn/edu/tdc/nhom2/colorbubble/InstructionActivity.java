package vn.edu.tdc.nhom2.colorbubble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_instruction);
        Button Back;
        Back = (Button)findViewById(R.id.btnBack);

        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }
        });

        final TextView tv1;
        final TextView tv2;
        final TextView tv3;
        final TextView tv4;
        final TextView tv5;

        Button btn;

        btn = (Button) findViewById(R.id.action);
        tv1 = (TextView) findViewById(R.id.txv1);
        tv2 = (TextView) findViewById(R.id.txv2);
        tv3 = (TextView) findViewById(R.id.txv3);
        tv4 = (TextView) findViewById(R.id.txv4);
        tv5 = (TextView) findViewById(R.id.txv5);




        btn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);//0.0f là độ mờ cao nhất(không thấy gì cả), 1.0f là độ mờ nhỏ nhất(chữ bình thường)
                animation1.setDuration(5000);// thời gian chữ bị mờ 5000 = 5 giây
                animation1.setStartOffset(1500);// thời gian lặp lại(nếu có cho lặp)
                animation1.setRepeatCount(1);// Số lần lặp lại
                tv1.startAnimation(animation1);
                tv2.startAnimation(animation1);
                tv3.startAnimation(animation1);
                tv4.startAnimation(animation1);
                tv5.startAnimation(animation1);
            }
        });
    }
}
