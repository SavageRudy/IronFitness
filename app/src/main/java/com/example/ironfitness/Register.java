package com.example.ironfitness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button reg;
    EditText email2, name, password,passconf;
    Spinner s1,s2;
    String text1,text2;
    boolean isNameValid, isEmailValid, isPasswordValid,isValid;
    Dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        reg = findViewById(R.id.register);
        email2 = findViewById(R.id.email2);
        password = findViewById(R.id.pass2);
        name = findViewById(R.id.name2);
        passconf = findViewById(R.id.passconf);


        s1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.subs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(this);


        s2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.desg, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter1);
        s2.setOnItemSelectedListener(this);

        db= new Dbhelper(this);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SetValidation()) {
                    String user = name.getText().toString();
                    String pass = password.getText().toString();
                    String email = email2.getText().toString();
                    switch (text2){
                        case "Member":
                                        if(!db.checkMember(user)){
                                            boolean insert = db.insertMembers(user,email,text1,pass);
                                            if(insert){
                                                Toast.makeText(getApplicationContext(), "Registered   Successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent1 = new Intent(Register.this, MainActivity.class);
                                                startActivity(intent1);
                                            }else{
                                                Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Member Already Exists", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                            case "Employee":
                            case "Trainer" :
                                            if(!db.checkWorker(user)) {
                                                boolean insert1 = db.insertWorker(user, email, text2, pass);
                                                if (insert1) {
                                                    Toast.makeText(getApplicationContext(), "Registered   Successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent1 = new Intent(Register.this, MainActivity.class);
                                                    startActivity(intent1);
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(getApplicationContext(), "Trainer/Employee Already Exists", Toast.LENGTH_SHORT).show();
                                            }
                                            break;
                            default: Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                }else{

                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean SetValidation() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            name.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
        }

        // Check for a valid email address.
        if (email2.getText().toString().isEmpty()) {
            email2.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email2.getText().toString()).matches()) {
            email2.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }


        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else if(!passconf.getText().toString().equals(password.getText().toString())) {
            password.setError(getResources().getString(R.string.passmatcherror));
            passconf.setError(getResources().getString(R.string.passmatcherror));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if (isNameValid && isEmailValid && isPasswordValid) {
            isValid = true;
        }else{
            isValid = false;
        }

        return isValid;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int id1 = parent.getId();
        switch(id1){
            case R.id.spinner1 :text1 = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner2 :text2 = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
