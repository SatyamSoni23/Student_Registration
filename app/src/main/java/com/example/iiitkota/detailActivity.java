package com.example.iiitkota;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class detailActivity extends AppCompatActivity {
    EditText name,password,email,id;
    Button back;
    public static String newEmail, newId, year, newName;
    DatabaseReference rootRef, demoRef,demoRef1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);
        back = findViewById(R.id.back);

        name.setText(loginActivity.newName);
        name.setEnabled(false);
        password.setText(loginActivity.newPassword);
        password.setEnabled(false);
        //id.setText(loginActivity.strYear);
        newName = loginActivity.newName;
        year = loginActivity.strYear;
        newId = Integer.toString(2019 - Integer.parseInt(year)) + "kucp" + newName;
        id.setText(newId);
        id.setEnabled(false);
        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("year").child(loginActivity.strYear);
        demoRef1 = demoRef.child(loginActivity.newName);
        demoRef1.child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newEmail = dataSnapshot.getValue().toString();
                email.setText(newEmail);
                email.setEnabled(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbackActivity();
            }
        });
    }
    public void openbackActivity(){
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }
}
