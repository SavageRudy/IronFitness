package com.example.ironfitness;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Token extends AppCompatActivity implements View.OnClickListener{
    Button sub , call1;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.token);
        sub = findViewById(R.id.submit);
        call1 = findViewById(R.id.call);
        sub.setOnClickListener(this);
        call1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit: Intent intent1 = new Intent(Token.this,Register.class);
                startActivity(intent1);
                break;
            case R.id.call:Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 12345678"));
                startActivity(intent2);
                break;
        }

    }
}

