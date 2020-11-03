package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import android.support.v7.widget.LinearLayoutManager;

import java.util.Arrays;
import java.util.List;

public class GroupActivity extends AppCompatActivity {

    Button btn_g_p1;
    Button MakingGroupButton;
    Button mygroup1, mygroup2, mygroup3, mygroup4;

    ImageButton tipButton;
    ImageButton staticButton;
    ImageButton homeButton;
    ImageButton groupButton;
    ImageButton profileButton;


    private RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        //홈버튼에서 각 버튼으로
        tipButton = findViewById(R.id.tipButton);
        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tipActivity로 전환
                Intent i = new Intent( GroupActivity.this, TipActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        staticButton = findViewById(R.id.staticButton);
        staticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //staticActivity로 전환
                Intent i = new Intent( GroupActivity.this, StaticActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //homeActivity로 전환
                Intent i = new Intent( GroupActivity.this, HomeActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profileActivity로 전환
                Intent i = new Intent( GroupActivity.this, ProfileActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        MakingGroupButton = findViewById(R.id.MakingGroup);
        MakingGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, MakeGroupActivity.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        mygroup1 = findViewById(R.id.group_icon1);
        mygroup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, find_group_popup1.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        mygroup2 = findViewById(R.id.group_icon2);
        mygroup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, find_group_popup1.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        mygroup3 = findViewById(R.id.group_icon3);
        mygroup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, find_group_popup1.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        mygroup4 = findViewById(R.id.group_icon4);
        mygroup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeGroupActivity로 전환
                Intent i = new Intent( GroupActivity.this, find_group_popup1.class);
                startActivity(i);//지정해 놓은 페이지로 화면 전환
            }
        });

        init();

        getData();

//        Button.OnClickListener btnListener = new View.OnClickListener(){
//            public void onClick(View v){
//                switch (v.getId()){
//                    case R.id.group_icon3:
//                        Intent intent = new Intent(GroupActivity.this, find_group_popup1.class);
//                        startActivityForResult(intent, 1);
//
//                        break;
//                }
//            }
//        };
//        btn_g_p1 = (Button)findViewById(R.id.group_icon3);
//        btn_g_p1.setOnClickListener(btnListener);
    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립",
                "국화", "사막", "수국", "해파리", "코알라", "등대", "펭귄", "튤립");
        List<String> listContent = Arrays.asList(
                "이 꽃은 국화입니다.",
                "여기는 사막입니다.",
                "이 꽃은 수국입니다.",
                "이 동물은 해파리입니다.",
                "이 동물은 코알라입니다.",
                "이것은 등대입니다.",
                "이 동물은 펭귄입니다.",
                "이 꽃은 튤립입니다.",
                "이 꽃은 국화입니다.",
                "여기는 사막입니다.",
                "이 꽃은 수국입니다.",
                "이 동물은 해파리입니다.",
                "이 동물은 코알라입니다.",
                "이것은 등대입니다.",
                "이 동물은 펭귄입니다.",
                "이 꽃은 튤립입니다."
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon,
                R.drawable.home_icon
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}