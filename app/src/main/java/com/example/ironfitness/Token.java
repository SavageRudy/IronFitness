package com.example.ironfitness;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Token extends AppCompatActivity implements View.OnClickListener{
    Button sub , call1;
    EditText tokken;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.token);
        sub = findViewById(R.id.submit);
        call1 = findViewById(R.id.call);
        tokken = findViewById(R.id.tokken1);
        sub.setOnClickListener(this);
        call1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                if(tokken.getText().toString().equals("abcd")) {
                    Intent intent1 = new Intent(Token.this, Register.class);
                    startActivity(intent1);
                }else {
                    Toast.makeText(Token.this,"Invalid Token",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.call:Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 12345678"));
                startActivity(intent2);
                break;
        }

    }
}

