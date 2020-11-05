package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    ImageButton iButton1;
    Chronometer Chorono;
    Button btnStart,btnEnd;
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
//    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
//    SimpleDateFormat sdfNow = new SimpleDateFormat("yy.MM.dd");
    // nowDate 변수에 값을 저장한다.
    String formatDate = "00:00:00:00";
    TextView dateNow;
    Calendar calendar = Calendar.getInstance();
    ListView listView;
    HealthAdapter adapter;
    ImageButton good_routine;



    //homebutton
    ImageButton tipButton;
    ImageButton staticButton;
    ImageButton homeButton;
    ImageButton groupButton;
    ImageButton profileButton;
    Button buttonstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //홈버튼에서 각 버튼으로
        tipButton = findViewById(R.id.tipButton);
        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tipActivity로 전환
                Intent i = new Intent( HomeActivity.this, TipActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        staticButton = findViewById(R.id.staticButton);
        staticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //staticActivity로 전환
                Intent i = new Intent( HomeActivity.this, StaticActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        groupButton = findViewById(R.id.groupButton);
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //groupActivity로 전환
                Intent i = new Intent( HomeActivity.this, GroupActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profileActivity로 전환
                Intent i = new Intent( HomeActivity.this, ProfileActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
//                finish();
            }
        });


        btnStart = (Button) findViewById(R.id.buttonstart);

//        Chorono = (Chronometer) findViewById(R.id.chronometer1);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent( HomeActivity.this, StartActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환

            }
        });

        DatabaseReference myRef = database.getReference("User/User1/laptime");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                formatDate = snapshot.getValue(String.class);
                dateNow = (TextView) findViewById(R.id.dateNow);
                dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //탭
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("Routine 1") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("Routine 2") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("Routine 3") ;
        tabHost1.addTab(ts3);


        //리스트뷰
        // Adapter 생성
        adapter = new HealthAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //추천 루틴 id연결
        good_routine = findViewById(R.id.good_routine);
    }
    public void mOnClick(View v) {
        EditText ed = (EditText) findViewById(R.id.newitem);
        switch (v.getId()) {
            case R.id.good_routine:
                // 추천 아이템 추가.
                adapter.addItem("Pull-up", 5 + "set", "None");
                adapter.addItem("Shoulder Press", 5 + "set", 10 + "kg");
                adapter.addItem("Bench Press", 5 + "set", 50 + "kg");
                adapter.addItem("Dumbbell row", 5 + "set", 20 + "kg");
                adapter.addItem("lateral raise", 5 + "set", 10 + "kg");
                adapter.addItem("Dips", 5 + "set", "None");
                adapter.notifyDataSetChanged();           // 리스트 목록 갱신
                break;
            case R.id.btnAdd:                                 // ADD 버튼 클릭시
                //firebase
                Intent select = new Intent( HomeActivity.this, HomeSaveActivity.class);
                startActivity(select);//지정해 놓은 페이지로 화면 전환


                //야매 집어넣기
                /*String text = ed.getText().toString();        // EditText에 입력된 문자열값을 얻기
                int set = 5;
                int weight = 20;
                if (!text.isEmpty()) {                        // 입력된 text 문자열이 비어있지 않으면
                    adapter.addItem(text, set + "set", weight + "kg");
                    ed.setText("");                           // EditText 입력란 초기화
                    adapter.notifyDataSetChanged();           // 리스트 목록 갱신
                }*/
                break;
            case R.id.btnDelete:                             // DELETE 버튼 클릭시
                adapter.delItem();
                adapter.notifyDataSetChanged();
                break;
        }
    }


}
