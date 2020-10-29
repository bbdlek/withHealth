package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;

public class find_group_popup1 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.group_find_popup1);
    }
}