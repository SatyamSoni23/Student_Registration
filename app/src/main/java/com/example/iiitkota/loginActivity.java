package com.example.iiitkota;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {
    private Button signup,login;
    public static String newName,newPassword,firePassword,strYear;
    int newYear =0;
    DatabaseReference rootRef, demoRef,demoRef1,demoRef2,demoRef3;
    EditText password,name,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        year = findViewById(R.id.year);

//***********************************For deleting text on focus**********************************

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    name.setHint("");
                }
                else{
                    name.setHint("Username");
                }
            }
        });
        year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    year.setHint("");
                else{
                    year.setHint("Year");
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

//*************************************************************************************************

        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("year");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strYear = year.getText().toString();
                newYear = Integer.parseInt(year.getText().toString());
                newName = name.getText().toString();
                newPassword = password.getText().toString();
                if(newYear >= 1 && newYear <=4){
                    demoRef1 = demoRef.child(strYear);
                    demoRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(newName).exists()){
                                demoRef2 = demoRef1.child(newName);
                                demoRef2.child("password").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        firePassword = dataSnapshot.getValue().toString();
                                        if(firePassword.equals(newPassword)){
                                            opendetailActivity();
                                        }
                                        else{
                                            openwrgActivity();
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        openerrorActivity();
                                    }
                                });
                            }
                            else{
                                openwrgActivity();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            openerrorActivity();
                        }
                    });
                }
                else{
                    openwrgActivity();
                }
            }
        });

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startsignupActivity();
            }
        });
    }
    public void startsignupActivity(){
        Intent intent = new Intent(this,signupActivity.class);
        startActivity(intent);
    }
    public void opendetailActivity(){
        Intent intent = new Intent(this, detailActivity.class);
        startActivity(intent);
    }
    public void openwrgActivity(){
        Intent intent = new Intent(this, wrgActivity.class);
        startActivity(intent);
    }
    public void openerrorActivity(){
        Intent intent = new Intent(this, errorActivity.class);
        startActivity(intent);
    }
}
