package com.cookandroid.swp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener{

    // 구글 맵 참조변수 생성
    GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setTitle("Map");

        MapView mapview = (MapView) findViewById(R.id.map);
        mapview.onCreate(savedInstanceState);
        mapview.onResume();
        mapview.getMapAsync(this);
    }

    //메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu((menu));
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.itemMap:
                Intent mapIntent = new Intent(getApplicationContext(),MapActivity.class);
                startActivity(mapIntent);
                return true;
            case R.id.itemLogout:
                Intent logoutIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(logoutIntent);
                return true;
            case R.id.itemMypage:
                Intent myPageIntent = new Intent(getApplicationContext(),MyPageActivity.class);
                startActivity(myPageIntent);
                return true;
        }
        return false;
    }

        //구글맵
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // 구글 맵 객체를 불러온다.
        mMap = googleMap;
        MarkerOptions makerOptions = new MarkerOptions();

        // 만우관에 대한 위치 설정
        LatLng Hansin3 = new LatLng(37.193091, 127.022944);
        makerOptions
                .position(Hansin3)
                .title("만우관");
        mMap.addMarker(makerOptions);

        // 샬롬채플관에 대한 위치 설정
        LatLng Hansin4 = new LatLng(37.1936, 127.0214);
        makerOptions
                .position(Hansin4)
                .title("샬롬채플관");
        mMap.addMarker(makerOptions);

        // 송암관에 대한 위치 설정
        LatLng Hansin7 = new LatLng(37.193482, 127.026408);
        makerOptions
                .position(Hansin7)
                .title("송암관");
        mMap.addMarker(makerOptions);

        // 소통관에 대한 위치 설정
        LatLng Hansin8 = new LatLng(37.1938, 127.0251);
        makerOptions
                .position(Hansin8)
                .title("소통관");
        mMap.addMarker(makerOptions);

        // 한울관에 대한 위치 설정
        LatLng Hansin10 = new LatLng(37.1942, 127.0199);
        makerOptions
                .position(Hansin10)
                .title("한울관");
        mMap.addMarker(makerOptions);

        // 해오름관에 대한 위치 설정
        LatLng Hansin17 = new LatLng(37.1942, 127.0227);
        makerOptions
                .position(Hansin17)
                .title("해오름관");
        mMap.addMarker(makerOptions);

        // 장준하통일관에 대한 위치 설정
        LatLng Hansin18 = new LatLng(37.1928, 127.0275);
        makerOptions
                .position(Hansin18)
                .title("장준하통일관");
        mMap.addMarker(makerOptions);
        mMap.setOnInfoWindowClickListener(this);

        // 늦봄관에 대한 위치 설정
        LatLng Hansin20 = new LatLng(37.192919, 127.023608);
        makerOptions
                .position(Hansin20)
                .title("늦봄관");
        mMap.addMarker(makerOptions);
    }


    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        Intent intent = new Intent(getApplicationContext(), ModeActivity.class);
        String title = marker.getTitle();
        intent.putExtra("title",title);
        startActivity(intent);
    }


}

    