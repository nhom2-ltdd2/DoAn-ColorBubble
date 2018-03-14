package vn.edu.tdc.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<data> arrayList = new ArrayList<>();
        arrayList.add(new data(R.drawable.b,"desert"));
        arrayList.add(new data(R.drawable.a,"jellyfish"));
        adapter ter =new adapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(ter);
    }

}
