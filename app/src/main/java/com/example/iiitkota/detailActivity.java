package com.example.iiitkota;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class detailActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    EditText name,password,email,id;
    Button back;
    public static String newEmail, newId, year, newName;
    DatabaseReference rootRef, demoRef,demoRef1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.myprofile){
                    Toast.makeText(detailActivity.this, "MyProfile", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.settings){
                    Toast.makeText(detailActivity.this, "settings", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.editprofile){
                    Toast.makeText(detailActivity.this, "EditProfile", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.iiitkimage);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
