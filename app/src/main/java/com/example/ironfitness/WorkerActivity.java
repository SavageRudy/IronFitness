package com.example.ironfitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WorkerActivity extends AppCompatActivity {
    Button signout;
    SharedPreferences session;
    TextView name , email, desg,stat,sal,work,stud;
    String username ,password;
    Worker1 worker = new Worker1();
    Dbhelper db = new Dbhelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        Log.i("reachd1","reachd7");
        worker = db.getdata2(username,password);
        Log.i("reachd1","reachd7");
        name.setText(worker.getName());
        email.setText(worker.getEmail());
        desg.setText(worker.getdesg());
        stat.setText(worker.getsstat());
        Log.i("reachd1","reachd2");
        sal.setText(String.valueOf(worker.getsal()));
        work.setText(worker.getwhours());
        stud.setText(String.valueOf(worker.getsid()));

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
