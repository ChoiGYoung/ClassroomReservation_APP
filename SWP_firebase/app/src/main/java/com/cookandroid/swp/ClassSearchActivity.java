package com.cookandroid.swp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ClassSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView c_name;
    private Button btnSearch;
    static boolean calledAlready = true;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    private ListView listView;
    private ArrayAdapter<String> adapter1;
    List<Object> Array = new ArrayList<Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classsearch);
        setTitle("강의실 조회");

        if(!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);

            calledAlready = true;
        }

        c_name = findViewById(R.id.c_name);

        //intent 사용해 ModeActivity 에서 건물 이름 & 번호 불러오기
        Intent intent = getIntent();
        String name = intent.getExtras().getString("title");
        c_name.setText(name);

        Intent intent2 = getIntent();
        Integer c_bdnum = intent2.getExtras().getInt("c_bdnum");

        //층 및 강의실 선택을 위한 spinner
        Spinner spinner = (Spinner) findViewById(R.id.spnFloor);//xml의 층 선택 스피너 id

        if (c_bdnum == 3) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.f_3_array,
                    android.R.layout.simple_spinner_dropdown_item);  //spinner 에 불러올 배열의 id, 레이아웃
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } else if (c_bdnum == 4) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.f_4_array,
                    android.R.layout.simple_spinner_item);  //spinner 에 불러올 배열의 id, 레이아웃
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } else if (c_bdnum == 7) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.f_7_array,
                    android.R.layout.simple_spinner_item);  //spinner 에 불러올 배열의 id, 레이아웃
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } else if (c_bdnum == 8) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.f_8_array,
                    android.R.layout.simple_spinner_item);  //spinner 에 불러올 배열의 id, 레이아웃
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } else if (c_bdnum == 10) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.f_10_array,
                    android.R.layout.simple_spinner_item);  //spinner 에 불러올 배열의 id, 레이아웃
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } else if (c_bdnum == 17) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.f_17_array,
                    android.R.layout.simple_spinner_item);  //spinner 에 불러올 배열의 id, 레이아웃
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } else if (c_bdnum == 18) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.f_18_array,
                    android.R.layout.simple_spinner_item);  //spinner 에 불러올 배열의 id, 레이아웃
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        } else if (c_bdnum == 20) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.f_20_array,
                    android.R.layout.simple_spinner_item);  //spinner 에 불러올 배열의 id, 레이아웃
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }

        //버튼, 리스트뷰 선언
        btnSearch = (Button) findViewById(R.id.btnSearch);
        listView = (ListView) findViewById(R.id.listView);

        initDatabase();

        //리스트뷰에 띄울 어댑터
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        listView.setAdapter(adapter1);





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //spinner에 floor 값이랑 db에 c_floor 값 같은거 가져와야댐
                    //버튼 누르면 db에서 정보 받아오기
                    btnSearch.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String text = spinner.getSelectedItem().toString();
                            int c_floor = Integer.valueOf(text);

                            mReference = mDatabase.getReference("CLASSROOM");
                            //mReference.orderByChild("c_floor").equalTo(c_floor).addValueEventListener(new ValueEventListener() {
                            mReference.orderByChild("c_bdnum").equalTo(c_bdnum.toString()).addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    adapter1.clear();
                                    for (DataSnapshot messageData : snapshot.getChildren()) {
                                        String msg = messageData.child("c_floor").toString();
                                        //c_floor랑 item

                                        // 안 예쁘게 뜨는 메시지 잘라주기(" "로 구분해서 7번째, 즉 강의실만 받아와서 realmsg에 저장)

                                        String realmsg = msg.split(" ")[7];

                                        if (c_floor == Integer.parseInt(realmsg)) {
                                            //확인용 로그
                                            // Log.d(TAG, msg);
                                            //Log.d(TAG, realmsg);
                                            String msg2 = messageData.child("c_seq").toString();
                                            String realmsg2 = msg2.split(" ")[7];
                                            Array.add(realmsg2);
                                            adapter1.add(realmsg2);
                                        }
                                        databaseReference.getRef();
                                    }
                                    adapter1.notifyDataSetChanged();
                                    listView.setSelection(adapter1.getCount() - 1);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.d(TAG, "onCancelled: Database Error!!!");
                                }
                            });
//                if(btnSearch.isClickable()){
//                    btnSearch.setClickable(false);
//                }

                        }

                    });
                }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),timetable_test.class);
                String title = adapter1.getItem(i).toString();
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });



    }

    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }





    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //spinner 에서 목록을 선택했을 때




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
