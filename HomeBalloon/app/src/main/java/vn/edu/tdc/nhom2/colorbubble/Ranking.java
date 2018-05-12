package vn.edu.tdc.nhom2.colorbubble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.tdc.nhom2.colorbubble.Model.Database;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;
import vn.edu.tdc.nhom2.colorbubble.adapter.RankAdapter;

public class Ranking extends AppCompatActivity {
    Database database;
    ImageView imgBackHome, imgSpeaker;
    ArrayList<Score> listScore = new ArrayList<>();
    ListView listView;
    RankAdapter adapterRank;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        imgBackHome = (ImageView) findViewById(R.id.btn_backhome);
        imgSpeaker = (ImageView) findViewById(R.id.btn_volume);
        listView = (ListView) findViewById(R.id.lv_rank);


        listScore.add(new Score("Lai",100,6));
        listScore.add(new Score("sss",80,6));
        listScore.add(new Score("đaa",55,6));
        listScore.add(new Score("xxxx",45,6));
        listScore.add(new Score("aaaaa",21,6));
        adapterRank = new RankAdapter(Ranking.this, R.layout.rank_custom, listScore);
        listView.setAdapter(adapterRank);


        imgBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backHomeScreen = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(backHomeScreen);
            }
        });

    }
}
