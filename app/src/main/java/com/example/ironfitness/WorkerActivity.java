package com.example.ironfitness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class WorkerActivity extends AppCompatActivity {
    Button signout;
    SharedPreferences session;
    TextView name , email, desg,stat,sal,work,stud;
    String username ,password;
    Worker1 worker = new Worker1();
    Dbhelper db = new Dbhelper(this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.worker);
        session = getSharedPreferences("session",MODE_PRIVATE);

        signout = findViewById(R.id.signout);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        desg = findViewById(R.id.desg);
        stat = findViewById(R.id.Status);
        sal = findViewById(R.id.sal);
        work = findViewById(R.id.working);
        stud = findViewById(R.id.Students);

        username= session.getString("username","");
        password = session.getString("password","");


        worker = db.getdata2(username,password);
        name.setText(worker.getName());
        email.setText(worker.getEmail());
        desg.setText(worker.getdesg());
        stat.setText(worker.getsstat());
        sal.setText(String.valueOf(worker.getsal()));
        work.setText(worker.getwhours());
        if(worker.getsid() != 0) {
            if (!db.getmembername(worker.getsid()).equals(null)) {
                stud.setText(String.valueOf(db.getmembername(worker.getId())));
            } else {
                stud.setText("User Dosnt Exist");
            }
        }else{
            stud.setText("NONE");
        }
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = session.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(WorkerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
