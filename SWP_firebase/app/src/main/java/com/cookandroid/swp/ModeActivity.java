package com.cookandroid.swp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class ModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        setTitle(" ");

        TextView tx1 =(TextView)findViewById(R.id.tx1);
        Intent intent = getIntent(); // 건물명
        String name = intent.getExtras().getString("title");
        tx1.setText(name);

        //"강의실 조회" 버튼를 눌렀을 때
        Button btnClass = (Button) findViewById(R.id.btnClass);
        btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MapActivity 에서 건물 이름을 인텐트로 받아오기
                Intent intent = new Intent(getApplicationContext(),ClassSearchActivity.class);
                intent.putExtra("title",name);

                //건물명에 따라 건물 번호 할당 후 인텐트에 추가하기
                int c_bdnum = 0;

                if (name.equals("만우관")) {
                    c_bdnum = 3;
                }
                else if (name.equals("샬롬채플관")) {
                    c_bdnum = 4;
                }
                else if (name.equals("송암관")) {
                    c_bdnum = 7;
                }
                else if (name.equals("소통관")) {
                    c_bdnum = 8;
                }
                else if (name.equals("한울관")) {
                    c_bdnum = 10;
                }
                else if (name.equals("해오름관")) {
                    c_bdnum = 17;
                }
                else if (name.equals("장준하통일관")) {
                    c_bdnum = 18;
                }
                else if (name.equals("늦봄관")) {
                    c_bdnum = 20;
                }


                intent.putExtra("c_bdnum", c_bdnum);
                startActivity(intent);
                //여기까지 하면 인텐트로 넘기는 값은 (String 건물이름, int 건물번호)
            }
        });

        //"시간표 조회" 버튼를 눌렀을 때
        Button btnTime = (Button) findViewById(R.id.btnTime);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TimeSearchActivity.class);
                intent.putExtra("title",name);
                startActivity(intent);
            }
        });
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