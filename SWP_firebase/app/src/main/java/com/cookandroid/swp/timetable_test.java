package com.cookandroid.swp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class timetable_test extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_test);

        TextView tx1 =(TextView)findViewById(R.id.tx1);
        Intent intent = getIntent(); // 건물명
        String text = intent.getExtras().getString("title");
        tx1.setText(text);


    }
}
