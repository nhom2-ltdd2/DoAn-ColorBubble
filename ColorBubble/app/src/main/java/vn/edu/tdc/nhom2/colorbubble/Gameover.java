package vn.edu.tdc.nhom2.colorbubble;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import vn.edu.tdc.nhom2.colorbubble.Model.Database;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;

public class Gameover extends AppCompatActivity{

    FragmentManager manager;
    FragmentTransaction transaction;
    FragmentManager manager1;
    FragmentTransaction transaction1;
    Fragment fragment ;
    Fragment fragment2 = new frg_bot_gamover();
    ArrayList<Score> arr = new ArrayList<>();
    int Score;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.gameover);

//        Intent intent = getIntent();
//
//        Bundle bundle = intent.getBundleExtra("Bundle");



        Database data = new Database(getApplicationContext());


        arr = data.getAllScore();
        if(arr.size() == 0){
            data.addScore(new Score("Ravi", 10,60));
            data.addScore(new Score("vinh", 9,60));
            data.addScore(new Score("truong", 8,60));
            data.addScore(new Score("tien", 7,60));
            data.addScore(new Score("dat", 6,60));
            data.addScore(new Score("khanh", 5,60));
            data.addScore(new Score("lai", 4,60));
            data.addScore(new Score("tuong", 3,60));
            data.addScore(new Score("hau", 2,60));
            data.addScore(new Score("hoa", 1,60));
            arr = data.getAllScore();
        }
//        Score = bundle.getInt("Score");
//        if(Score > arr.get(9).getScore()){
//            arr.remove(9);
//            dialog = new Dialog(getApplicationContext());
//            dialog.setContentView(R.layout.dialog_getavatar);
//            dialog.setTitle("Choose Avatar Image");
//            arr.add()
//        }



        fragment = new frg_top_gamover();
//        fragment.setArguments(bundle);



        manager= getSupportFragmentManager();
        transaction= manager.beginTransaction();

        manager1= getSupportFragmentManager();
        transaction1= manager1.beginTransaction();

        transaction.setCustomAnimations(R.anim.frg_down, R.anim.frg_up);
        transaction.add(R.id.up, fragment, "Fragment_Fill_Patient");


        transaction.commit();

        transaction1.setCustomAnimations(R.anim.frg_up, R.anim.frg_down);
        transaction1.add(R.id.down, fragment2, "Fragment_Fill_Patient");


        transaction1.commit();









//        transaction.replace(R.id.so1, fragment2, "Fragment_Fill_Patient");
//
//        transaction.commit();



    }


}
