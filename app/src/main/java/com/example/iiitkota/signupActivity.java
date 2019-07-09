package com.example.iiitkota;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signupActivity extends AppCompatActivity {
    DatabaseReference rootRef, demoRef,demoRef1,demoRef2;
    EditText name,password,email,year;
    Button signup,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.iiitkimage);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        login = (Button) findViewById(R.id.login);
        name = (EditText) findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        year = (EditText) findViewById(R.id.year);
        signup = (Button) findViewById(R.id.signup);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    name.setHint("");
                else{
                    name.setHint("Name");
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    password.setHint("");
                else{
                    password.setHint("Password");
                }
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    email.setHint("");
                else {
                    email.setHint("Email");
                }
            }
        });
        year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    year.setHint("");
                else {
                    year.setHint("Year");
                }
            }
        });

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("year");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stryear = year.getText().toString();
                String strname = name.getText().toString();
                String stremail = email.getText().toString();
                String strpassword = password.getText().toString();
                int num = Integer.parseInt(stryear);
                if(num >=1 && num <=4) {
                    demoRef1 = demoRef.child(stryear);
                    demoRef2 = demoRef1.child(strname);
                    demoRef2.child("name").setValue(strname);
                    demoRef2.child("email").setValue(stremail);
                    demoRef2.child("password").setValue(strpassword);
                    demoRef2.child("year").setValue(stryear);
                    opensignupActivity();
                }
                else{
                    opentryagainactivity();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startloginActivity();
            }
        });
    }
    public void startloginActivity(){
        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
    }
    public void opensignupActivity(){
        Intent intent = new Intent(this,successSignupActivity.class);
        startActivity(intent);
    }
    public void opentryagainactivity(){
        Intent intent = new Intent(this, tryAgainActivity.class);
        startActivity(intent);
    }
}
