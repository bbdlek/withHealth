package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    ImageButton iButton1;
    Chronometer Chorono;
    Button btnStart,btnEnd;
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yy.MM.dd");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    TextView dateNow;
    Calendar calendar = Calendar.getInstance();

    //homebutton
    ImageButton tipButton;
    ImageButton staticButton;
    ImageButton homeButton;
    ImageButton groupButton;
    ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        btnEnd=(Button)findViewById(R.id.buttonend);
        Chorono = (Chronometer) findViewById(R.id.chronometer1);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chorono.setBase(SystemClock.elapsedRealtime());
                Chorono.start();
                Chorono.setTextColor(Color.WHITE);
                Toast.makeText(getApplicationContext(),"시작",Toast.LENGTH_SHORT).show();
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chorono.stop();
                Chorono.setTextColor(Color.WHITE);
                Toast.makeText(getApplicationContext(),"종료",Toast.LENGTH_SHORT).show();
            }
        });
        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당

    }
}
