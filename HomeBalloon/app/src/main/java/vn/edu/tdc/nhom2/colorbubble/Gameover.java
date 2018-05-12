package vn.edu.tdc.nhom2.colorbubble;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vn.edu.tdc.nhom2.colorbubble.Model.Database;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;

public class Gameover extends AppCompatActivity {

    FragmentManager manager;
    FragmentTransaction transaction;
    FragmentManager manager1;
    FragmentTransaction transaction1;
    Fragment fragment;
    Fragment fragment2 = new frg_bot_gamover();
    ArrayList<Score> arr = new ArrayList<>();
    int Score;
    int Time;
    TextView text;
    Button cofirm;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gameover);


        Intent intent = getIntent();

        final Bundle bundle = intent.getBundleExtra("Bundle");


        final Database data = new Database(getApplicationContext());


        arr = data.getAllScore();
        if (arr.size() == 0) {
            data.addScore(new Score("Ravi", 10, 60));
            data.addScore(new Score("Vinh", 9, 60));
            data.addScore(new Score("Truong", 8, 60));
            data.addScore(new Score("Tien", 7, 60));
            data.addScore(new Score("Dat", 6, 60));
            data.addScore(new Score("Khanh", 5, 60));
            data.addScore(new Score("Tai", 4, 60));
            data.addScore(new Score("Tuong", 3, 60));
            data.addScore(new Score("Hau", 2, 60));
            data.addScore(new Score("Hoa", 1, 60));
            arr = data.getAllScore();
        }
        Score = bundle.getInt("Score");
        Time = bundle.getInt("Time");


        if (Score > arr.get(9).getScore()) {
            arr.remove(9);
            dialog = new Dialog(Gameover.this);
            dialog.setContentView(R.layout.dialog_entername);
            text = (TextView) dialog.findViewById(R.id.input_name);
            cofirm = (Button) dialog.findViewById(R.id.cofirm);
            text.setGravity(Gravity.CENTER);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle("Enter your name");
            dialog.show();
            cofirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arr.add(new Score(text.getText().toString(), Score, Time));
                    data.deleteAllScore();
                    Collections.sort(arr, new Comparator<Score>() {
                        @Override
                        public int compare(Score o1, Score o2) {
                            if (o1.score < o2.score) {
                                return 1;
                            } else {
                                if (o1.score == o2.score) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        }
                    });
                    for (Score a : arr) {
                        data.addScore(a);
                    }
                    dialog.dismiss();
                    fragment = new frg_top_gamover();
                    fragment.setArguments(bundle);


                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();

                    manager1 = getSupportFragmentManager();
                    transaction1 = manager1.beginTransaction();

                    transaction.setCustomAnimations(R.anim.frg_down, R.anim.frg_up);
                    transaction.add(R.id.up, fragment, "Fragment_Fill_Patient");
                    transaction.commit();

                    transaction1.setCustomAnimations(R.anim.frg_up, R.anim.frg_down);
                    transaction1.add(R.id.down, fragment2, "Fragment_Fill_Patient");
                    transaction1.commit();

                }
            });

        } else {


            fragment = new frg_top_gamover();
            fragment.setArguments(bundle);


            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();

            manager1 = getSupportFragmentManager();
            transaction1 = manager1.beginTransaction();

            transaction.setCustomAnimations(R.anim.frg_down, R.anim.frg_up);
            transaction.add(R.id.up, fragment, "Fragment_Fill_Patient");
            transaction.commit();

            transaction1.setCustomAnimations(R.anim.frg_up, R.anim.frg_down);
            transaction1.add(R.id.down, fragment2, "Fragment_Fill_Patient");
            transaction1.commit();
        }


    }


}
