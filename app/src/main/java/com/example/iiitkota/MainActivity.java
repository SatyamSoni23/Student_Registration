package com.example.iiitkota;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.cont);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencontActivity();
            }
        });
    }
    public void opencontActivity(){
        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
    }
}
