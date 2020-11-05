package com.example.ironfitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MemberActivity extends AppCompatActivity {
    SharedPreferences session;
    Button signout;
    Dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member);

        session = getSharedPreferences("session",MODE_PRIVATE);

        signout = findViewById(R.id.signout);



        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = session.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MemberActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }}
