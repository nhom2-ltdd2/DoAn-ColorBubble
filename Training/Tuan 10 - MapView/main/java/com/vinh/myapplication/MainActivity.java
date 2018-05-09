package com.vinh.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MapFragment map = (MapFragment) getFragmentManager().findFragmentById(R.id.mymap);
        map.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(10.8522445, 106.7564006);

        LatLng sydney1 = new LatLng(10.850930,106.755059);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Cao Dang "));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));

        googleMap.addMarker(new MarkerOptions().position(sydney1)
                .title("Marker in Sydney"));


    }
}
