package vn.edu.tdc.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnCong, btnTru, btnNhan, btnChia, btnBang, btnC;
    EditText edt;
    Integer a = 0;
    String abc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnCong = (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
        btnC = (Button) findViewById(R.id.btnC);
        btnBang = (Button) findViewById(R.id.btnBang);

        edt = (EditText) findViewById(R.id.edt);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnBang.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        switch (b.getId()) {
            case R.id.btn0:
                Log.d("result", "0");
                edt.setText(edt.getText().toString() + "0");
                break;
            case R.id.btn1:
                Log.d("result", "1");
                edt.setText(edt.getText().toString() + "1");
                break;
            case R.id.btn2:
                Log.d("result", "2");
                edt.setText(edt.getText().toString() + "2");
                break;
            case R.id.btn3:
                Log.d("result", "3");
                edt.setText(edt.getText().toString() + "3");
                break;
            case R.id.btn4:
                Log.d("result", "4");
                edt.setText(edt.getText().toString() + "4");
                break;
            case R.id.btn5:
                Log.d("result", "5");
                edt.setText(edt.getText().toString() + "5");
                break;
            case R.id.btn6:
                Log.d("result", "6");
                edt.setText(edt.getText().toString() + "6");
                break;
            case R.id.btn7:
                Log.d("result", "7");
                edt.setText(edt.getText().toString() + "7");
                break;
            case R.id.btn8:
                Log.d("result", "8");
                edt.setText(edt.getText().toString() + "8");
                break;
            case R.id.btn9:
                Log.d("result", "9");
                edt.setText(edt.getText().toString() + "9");
                break;
            case R.id.btnCong:
                Log.d("result", "Cong");
                a = Integer.parseInt(edt.getText().toString());
                edt.setText(edt.getText().toString() + "+");
                if (abc == "") {
                    abc = "Cong";
                }
                break;
            case R.id.btnTru:
                Log.d("result", "Tru");
                a = Integer.parseInt(edt.getText().toString());
                edt.setText(edt.getText().toString() + "-");
                if (abc == "") {
                    abc = "Tru";
                }
                break;
            case R.id.btnNhan:
                Log.d("result", "Nhan");
                a = Integer.parseInt(edt.getText().toString());
                edt.setText(edt.getText().toString() + "*");
                if (abc == "") {
                    abc = "Nhan";
                }
                break;
            case R.id.btnChia:
                Log.d("result", "Chia");
                a = Integer.parseInt(edt.getText().toString());
                edt.setText(edt.getText().toString() + "/");
                if (abc == "") {
                    abc = "Chia";
                }
                break;
            case R.id.btnC:
                Log.d("result", "C");
                edt.setText("");
                abc = "";
                a = 0;
                break;
            case R.id.btnBang:
                Log.d("result", "Bang");
                Integer kq;
                switch (abc) {
                    case "Cong":
                        kq = Integer.parseInt(edt.getText().toString().split("\\+")[1]) + a;
                        edt.setText(kq.toString());
                        break;
                    case "Tru":
                        kq = a - Integer.parseInt(edt.getText().toString().split("-")[1]);
                        edt.setText(kq.toString());
                        break;
                    case "Nhan":
                        kq = Integer.parseInt(edt.getText().toString().split("\\*")[1]) * a;
                        edt.setText(kq.toString());
                        break;
                    case "Chia":
                        kq = a / Integer.parseInt(edt.getText().toString().split("/")[1]);
                        edt.setText(kq.toString());
                        break;
                }
                break;
        }
    }
}
