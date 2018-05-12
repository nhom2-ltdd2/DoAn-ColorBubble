package vn.edu.tdc.nhom2.colorbubble;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import vn.edu.tdc.nhom2.colorbubble.Model.Database;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;
import vn.edu.tdc.nhom2.colorbubble.adapter.adatapter;

/**
 * Created by Vinh on 21-Apr-18.
 */

public class frg_bot_gamover extends android.support.v4.app.Fragment {

    private ViewPager mPager;
    ArrayList<Score> ar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.frg_bot_gameover, container, false);
        Button playagain,menu;
        playagain = (Button)view.findViewById(R.id.play);
        menu = (Button)view.findViewById(R.id.menu);

        mPager = (ViewPager) view.findViewById(R.id.myfivepanelpager);
        ar = new ArrayList<>();
        Database data = new Database(getActivity());
        ar = data.getAllScore();

        adatapter adapter = new adatapter(getContext(), ar);



        mPager.setAdapter(adapter);
        mPager.setCurrentItem(0);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
