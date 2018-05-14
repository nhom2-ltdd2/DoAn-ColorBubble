package vn.edu.tdc.nhom2.colorbubble;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vn.edu.tdc.nhom2.colorbubble.Model.Database;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;

public class Gameover extends AppCompatActivity {

    private static final int CAM_REQUEST = 1313;
    Bitmap thumbnail;
    FragmentManager manager;
    FragmentTransaction transaction;
    FragmentManager manager1;
    FragmentTransaction transaction1;
    Fragment fragment;
    Fragment fragment2 = new frg_bot_gamover();
    ArrayList<Score> arr = new ArrayList<>();
    int Score;
    int Time;
    String hinh;
    TextView text;
    Button cofirm;
    private Dialog dialog ;

    ImageView image;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gameover);
        dialog = new Dialog(Gameover.this);;
        dialog.setContentView(R.layout.dialog_entername);



        Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra("Bundle");

        final Database data = new Database(getApplicationContext());



        Score = bundle.getInt("Score");
        Time = bundle.getInt("Time");
        arr = data.getAllScore();




        if (Score > arr.get(9).getScore()) {
            arr.remove(9);

            text = (TextView) dialog.findViewById(R.id.input_name);
            cofirm = (Button) dialog.findViewById(R.id.cofirm);
            image = (ImageView) dialog.findViewById(R.id.image);
            image.setImageBitmap(WebViewActivity.getScreenshot());
            text.setGravity(Gravity.CENTER);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            cofirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arr.add(new Score(text.getText().toString(), Score, Time,convertToBase64(WebViewActivity.getScreenshot())));
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

//    @Override
//    protected void onPostResume() {
//
//        dialog.show();
//        super.onPostResume();
//    }






    private  String convertToBase64(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] byteArrayImage = baos.toByteArray();

        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

        return encodedImage;

    }



}
