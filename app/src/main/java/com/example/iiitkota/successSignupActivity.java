package com.example.iiitkota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class successSignupActivity extends AppCompatActivity {
    Button login,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_signup);

        login = findViewById(R.id.login);
        home = findViewById(R.id.home);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startloginActivity();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starthomeActivity();
            }
        });
    }
    public void startloginActivity(){
        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
    }
    public void starthomeActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
