package vn.edu.tdc.nhom2.colorbubble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

public class Gameover extends AppCompatActivity{

    FragmentManager manager;
    FragmentTransaction transaction;
    FragmentManager manager1;
    FragmentTransaction transaction1;
    Fragment fragment ;
    Fragment fragment2 = new frg_bot_gamover();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.gameover);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("Bundle");
        Log.d("a",bundle.getInt("Score")+" ");


        fragment = new frg_top_gamover();
        fragment.setArguments(bundle);



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
