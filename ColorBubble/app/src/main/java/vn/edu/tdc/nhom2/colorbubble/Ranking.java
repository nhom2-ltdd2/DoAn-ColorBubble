package vn.edu.tdc.nhom2.colorbubble;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.tdc.nhom2.colorbubble.Model.RankModel;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;
import vn.edu.tdc.nhom2.colorbubble.adapter.RankAdapter;
import vn.edu.tdc.nhom2.colorbubble.adapter.adatapter;

public class Ranking extends AppCompatActivity {
    ImageView imgBackHome, imgSpeaker;
    ArrayList<RankModel> listRank = new ArrayList<>();
    ListView listView;
    RankAdapter adapterRank;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        imgBackHome = (ImageView) findViewById(R.id.btn_backhome);
        imgSpeaker = (ImageView) findViewById(R.id.btn_volume);
        listView = (ListView) findViewById(R.id.lv_rank);

        listRank.add(new RankModel("Lai",100,6));
        listRank.add(new RankModel("sss",80,6));
        listRank.add(new RankModel("đaa",55,6));
        listRank.add(new RankModel("xxxx",45,6));
        listRank.add(new RankModel("aaaaa",21,6));
        adapterRank = new RankAdapter(Ranking.this, R.layout.rank_custom, listRank);
        listView.setAdapter(adapterRank);


        imgBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backHomeScreen = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(backHomeScreen);
            }
        });

    }
}
