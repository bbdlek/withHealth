package com.example.my_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartActivity extends AppCompatActivity {

    private Button mStartBtn, mStopBtn, mPauseBtn;
    private TextView mTimeTextView;
    private ImageButton sound_on,sound_off;
    private Thread timeThread = null;
    private Boolean isRunning = true;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("User");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#4ea1d3"));
        }

        mStartBtn = findViewById(R.id.b_play);
        mStopBtn = findViewById(R.id.b_reset);
        mPauseBtn = findViewById(R.id.b_pause);
        mTimeTextView = findViewById(R.id.timer_text);
        sound_off=findViewById(R.id.sound_off);
        sound_on=findViewById(R.id.sound_on);

        sound_off.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                sound_on.setVisibility(View.VISIBLE);
            }
        });

        sound_on.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                sound_off.setVisibility(View.VISIBLE);

            }
        });

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                mPauseBtn.setVisibility(View.VISIBLE);
                timeThread = new Thread(new timeThread());
                timeThread.start();
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStartBtn.setVisibility(View.VISIBLE);
                mPauseBtn.setVisibility(View.GONE);
                mTimeTextView.setText("00:00:00:00");
                timeThread.interrupt();
            }
        });

        mPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = !isRunning;
                mPauseBtn.setText(isRunning ? "일시정지" : "시작");
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent( StartActivity.this, HomeActivity.class);
        startActivity(i);//지정해 놓은 페이지로 화면 전환
    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;
            int hour = (msg.arg1 / 100) / 360;
            //1000이 1초 1000*60 은 1분 1000*60*10은 10분 1000*60*60은 한시간

            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d:%02d", hour, min, sec, mSec);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            mTimeTextView.setText(result);
            DatabaseReference grRef = myRef.child(uid);
            grRef.child("laptime").setValue(mTimeTextView.getText().toString());
        }
    };


    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                while (isRunning) { //일시정지를 누르면 멈춤
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                mTimeTextView.setText("");
                                mTimeTextView.setText("00:00:00:00");
                            }
                        });
                        return; // 인터럽트 받을 경우 return
                    }
                }
            }
        }
    }
}