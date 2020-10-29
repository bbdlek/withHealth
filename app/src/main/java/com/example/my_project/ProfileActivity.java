package com.example.my_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.DatePicker;

public class ProfileActivity extends AppCompatActivity {
    TextView nickname, btnheight,btnweight,bmi;
    ImageButton editnick;
    EditText Edtname,Edtheight,Edtweight;
    View dialogView;
    Button btnsex;
    final int DIALOG_DATE = 1;

    //homebutton
    ImageButton tipButton;
    ImageButton staticButton;
    ImageButton homeButton;
    ImageButton groupButton;
    ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //홈버튼에서 각 버튼으로
        tipButton = findViewById(R.id.tipButton);
        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tipActivity로 전환
                Intent i = new Intent( ProfileActivity.this, TipActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        staticButton = findViewById(R.id.staticButton);
        staticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //staticActivity로 전환
                Intent i = new Intent( ProfileActivity.this, StaticActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //homeActivity로 전환
                Intent i = new Intent( ProfileActivity.this, HomeActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        groupButton = findViewById(R.id.groupButton);
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //groupActivity로 전환
                Intent i = new Intent( ProfileActivity.this, GroupActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        nickname = (TextView) findViewById(R.id.nickname);
        editnick = (ImageButton) findViewById(R.id.editnick);
        editnick.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialogView = (View) View.inflate(ProfileActivity.this, R.layout.prof, null);
                AlertDialog.Builder dlg= new AlertDialog.Builder(ProfileActivity.this);
                dlg.setTitle("닉네임 설정");
                dlg.setIcon(R.drawable.profile_icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Edtname=(EditText) dialogView.findViewById(R.id.Edtnick);
                        nickname.setText(Edtname.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
        btnsex=(Button)findViewById(R.id.btnsex);
        btnsex.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final String[] items= new String[]{"남자", "여자", "표기 안함"};
                final int[] selectedindex={0};
                AlertDialog.Builder dialog=new AlertDialog.Builder(ProfileActivity.this);
                dialog.setTitle("성별을 선택하세요.");
                dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedindex[0]=i;
                    }
                });
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btnsex.setText("남자");
                    }
                }).create().show();
            }
        });
        btnheight = (TextView) findViewById(R.id.btnheight);
        btnheight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialogView = (View) View.inflate(ProfileActivity.this, R.layout.profile_height, null);
                AlertDialog.Builder dlg= new AlertDialog.Builder(ProfileActivity.this);
                dlg.setTitle("키를 입력하세요");
                dlg.setIcon(R.drawable.profile_icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Edtheight=(EditText) dialogView.findViewById(R.id.Edtheight);
                        btnheight.setText(Edtheight.getText().toString()+"cm");
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
        btnweight = (TextView) findViewById(R.id.btnweight);
        btnweight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialogView = (View) View.inflate(ProfileActivity.this, R.layout.profile_weight, null);
                AlertDialog.Builder dlg= new AlertDialog.Builder(ProfileActivity.this);
                dlg.setTitle("몸무게를 입력하세요");
                dlg.setIcon(R.drawable.profile_icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Edtweight=(EditText) dialogView.findViewById(R.id.Edtweight);
                        btnweight.setText(Edtheight.getText().toString()+"kg");
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
        bmi=(TextView)findViewById(R.id.bmi);
        double Bmi=23;
        if(Bmi<=15.0) {
            bmi.setText("당신은 심각한 저체중으로 사망위험도는 2.76입니다.");
        }
        if(Bmi>15.0 && Bmi<=20.0) {
            bmi.setText("당신은 저체중과 정상의 중간경계로 사망위험도는 1.35입니다.");
        }
        if(Bmi>20.0&&Bmi<=22.6){
            bmi.setText("당신은 정상체중이며 사망위험도는 1.09입니다.");
        }
        if(Bmi>22.6 && Bmi<=30.0) {
            bmi.setText("단신은 정상과 과체중의 중간경계로 사망위험도는 1.03입니다.");
        }
        if(Bmi>30.0) {
            bmi.setText("단신은 고도비만으로 사망위험도는 1.5입니다.");
        }
    }
}