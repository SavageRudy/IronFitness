package com.example.ironfitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity extends AppCompatActivity {
    Button signout;
    SharedPreferences session;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);
        session = getSharedPreferences("session",MODE_PRIVATE);

        signout = findViewById(R.id.signout);



        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = session.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(ManagerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
