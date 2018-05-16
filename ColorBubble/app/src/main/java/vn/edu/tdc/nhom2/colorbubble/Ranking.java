package vn.edu.tdc.nhom2.colorbubble;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.tdc.nhom2.colorbubble.Model.Database;
import vn.edu.tdc.nhom2.colorbubble.Model.Score;
import vn.edu.tdc.nhom2.colorbubble.adapter.RankAdapter;

public class Ranking extends AppCompatActivity {
    Database database;
    ImageView imgBackHome, imgSpeaker;
    ArrayList<Score> listScore;
    ListView listView;
    RankAdapter adapterRank;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ranking);
        imgBackHome = (ImageView) findViewById(R.id.btn_backhome);
        imgSpeaker = (ImageView) findViewById(R.id.btn_volume);
        listView = (ListView) findViewById(R.id.lv_rank);
        database = new Database(getApplicationContext());
        listScore = database.getScore();
        //hello cau


        adapterRank = new RankAdapter(Ranking.this, R.layout.rank_custom, listScore);
        listView.setAdapter(adapterRank);

        listView.setOnItemLongClickListener(new  ItemOnClickRemove());



        imgBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backHomeScreen = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(backHomeScreen);
            }
        });

    }
    private class OnClickImage implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }
    private class ItemOnClickRemove implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ranking.this);
            alertDialogBuilder.setMessage("Bạn Có Muốn Xóa Không");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    listScore.remove(position);
                    adapterRank.notifyDataSetChanged();
                    listScore = database.getScore();
                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {

                }
            });
            alertDialogBuilder.show();
            return true;
        }

    }
}
