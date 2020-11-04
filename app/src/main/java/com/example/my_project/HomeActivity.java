package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    ListView listView;


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
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profileActivity로 전환
                Intent i = new Intent( HomeActivity.this, ProfileActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
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


        //리스트뷰
        items=new ArrayList<String>();
        items.add("바벨로우");
        items.add("벤치 프레스");
        items.add("숄더 프레스");
        items.add("스쿼트");
        adapter=new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_single_choice, items);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
    public void mOnClick(View v) {
        EditText ed = (EditText) findViewById(R.id.newitem);
        switch (v.getId()) {
            case R.id.btnAdd:                                 // ADD 버튼 클릭시
                String text = ed.getText().toString();        // EditText에 입력된 문자열값을 얻기
                if (!text.isEmpty()) {                        // 입력된 text 문자열이 비어있지 않으면
                    items.add(text);                          // items 리스트에 입력된 문자열 추가
                    ed.setText("");                           // EditText 입력란 초기화
                    adapter.notifyDataSetChanged();           // 리스트 목록 갱신
                }
                break;
            case R.id.btnDelete:                             // DELETE 버튼 클릭시
                int pos = listView.getCheckedItemPosition(); // 현재 선택된 항목의 첨자(위치값) 얻기
                if (pos != ListView.INVALID_POSITION) {      // 선택된 항목이 있으면
                    items.remove(pos);                       // items 리스트에서 해당 위치의 요소 제거
                    listView.clearChoices();                 // 선택 해제
                    adapter.notifyDataSetChanged();
                    // 어답터와 연결된 원본데이터의 값이 변경된을 알려 리스트뷰 목록 갱신
                }
                break;
        }
    }


}
