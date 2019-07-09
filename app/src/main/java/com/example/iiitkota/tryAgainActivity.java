package com.example.iiitkota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tryAgainActivity extends AppCompatActivity {
    Button signup,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_again);
        signup = findViewById(R.id.signup);
        home = findViewById(R.id.home);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignupActivity();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhomeActivity();
            }
        });
    }
    public void opensignupActivity(){
        Intent intent = new Intent(this, signupActivity.class);
        startActivity(intent);
    }
    public void openhomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
