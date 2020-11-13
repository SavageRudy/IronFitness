package com.example.ironfitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ManagerActivity extends AppCompatActivity {
    Button signout;
    SharedPreferences session;
    Dbhelper db = new Dbhelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.manager);
        session = getSharedPreferences("session",MODE_PRIVATE);

        signout = findViewById(R.id.signout);

        SimpleCursorAdapter sadapter = new SimpleCursorAdapter(this,R.layout.displist,db.getallmembers(),new String[] {"name"},new int[] {R.id.displist1},0);
        ListView listView = (ListView) findViewById(R.id.memlist);
        listView.setAdapter(sadapter);



        SimpleCursorAdapter sadapter2 = new SimpleCursorAdapter(this,R.layout.displist,db.getallworkers(),new String[] {"name", "desg"},new int[] {R.id.displist1, R.id.displist2},0);
        ListView listView2 = (ListView) findViewById(R.id.worlist);
        listView2.setAdapter(sadapter2);



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
