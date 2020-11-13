package com.example.ironfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    Button button2,button3;
    Dbhelper db;
    EditText username, password;
    SharedPreferences session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_main);
        button2 = findViewById(R.id.login);
        button3 = findViewById(R.id.register);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.pass1);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);



        session = getSharedPreferences("session",MODE_PRIVATE);
        if(session.contains("username") && session.contains("password") && session.contains("desg")) {
            if (session.getString("desg", "").equals("Member")) {
                Intent intent1 = new Intent(MainActivity.this,MemberActivity.class);
                startActivity(intent1);
            }else if (session.getString("desg", "").equals("Worker")){
                Intent intent1 = new Intent(MainActivity.this,WorkerActivity.class);
                startActivity(intent1);
            }else if (session.getString("desg", "").equals("Manager")){
                Intent intent1 = new Intent(MainActivity.this,ManagerActivity.class);
                startActivity(intent1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                db = new Dbhelper(this);
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter the credentials",Toast.LENGTH_SHORT).show();
                }else if(db.checkUser1(user,pass)){
                    Intent intent2 = new Intent(MainActivity.this,MemberActivity.class);
                    SharedPreferences.Editor editor = session.edit();
                    editor.putString("username",user);
                    editor.putString("password",pass);
                    editor.putString("desg","Member");
                    editor.commit();
                    startActivity(intent2);
                }else if ( db.checkUser2(user,pass)){
                        Intent intent2 = new Intent(MainActivity.this,WorkerActivity.class);
                        SharedPreferences.Editor editor = session.edit();
                        editor.putString("username",user);
                        editor.putString("password",pass);
                        editor.putString("desg","Worker");
                        editor.commit();
                        startActivity(intent2);
                }else if ( user.equals("jake") && pass.equals("jake123")){
                    Intent intent2 = new Intent(MainActivity.this,ManagerActivity.class);
                    SharedPreferences.Editor editor = session.edit();
                    editor.putString("username",user);
                    editor.putString("password",pass);
                    editor.putString("desg","Manager");
                    editor.commit();
                    startActivity(intent2);
                }else{
                        Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                break;
            case R.id.register: Intent intent3 = new Intent(MainActivity.this,Token.class);
                startActivity(intent3);
                break;

        }

    }
}


