package com.cookandroid.swp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class TimeSearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesearch);
        setTitle("시간표 조회");

        TextView tx1 =(TextView)findViewById(R.id.tx1);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("title");
        tx1.setText(name);

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
}