package com.example.iiitkota;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editprofileActivity extends AppCompatActivity {
    EditText name, email, password, year;
    Button change, home;
    DatabaseReference rootRef, demoRef,demoRef1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        year = findViewById(R.id.year);
        change = findViewById(R.id.change);
        home = findViewById(R.id.home);

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    email.setHint("");
                }
                else{
                    email.setHint(" Email");
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    password.setHint("");
                }
                else{
                    password.setHint(" Password");
                }
            }
        });
        name.setText(loginActivity.newName);
        name.setEnabled(false);
        year.setText(loginActivity.strYear);
        year.setEnabled(false);

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("year").child(loginActivity.strYear);
        demoRef1 = demoRef.child(loginActivity.newName);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stremail = email.getText().toString();
                String strpassword = password.getText().toString();
                demoRef1.child("email").setValue(stremail);
                demoRef1.child("password").setValue(strpassword);
                openchangeActivity();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
    }
    public void openchangeActivity(){
        Intent intent = new Intent(this,changeActivity.class);
        startActivity(intent);
    }
    public void openHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
