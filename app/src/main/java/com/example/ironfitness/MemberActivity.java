package com.example.ironfitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MemberActivity extends AppCompatActivity {
    SharedPreferences session;
    Button signout,pay;
    Dbhelper db = new Dbhelper(this);
    String username,password;
    TextView name,subs,email,valid,status;
    Member1 member1 = new Member1();
    Member1 member2 = new Member1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();


        setContentView(R.layout.member);

        session = getSharedPreferences("session",MODE_PRIVATE);
        name = findViewById(R.id.name);
        subs = findViewById(R.id.subs);
        signout = findViewById(R.id.signout);
        email = findViewById(R.id.email);
        valid = findViewById(R.id.valid);
        status = findViewById(R.id.Status);
        pay = findViewById(R.id.pay);

        username= session.getString("username","");
        password = session.getString("password","");



        member1 = db.getdata(username,password);

        name.setText(member1.getName());
        subs.setText(member1.getsubs());
        email.setText(member1.getEmail());
        valid.setText(member1.getvald());
        status.setText(member1.getstat());


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

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = member1.getId();
                db.updatestat(id);
                member2 = db.getdata(username,password);
                status.setText(member2.getstat());
            }
        });
    }}
