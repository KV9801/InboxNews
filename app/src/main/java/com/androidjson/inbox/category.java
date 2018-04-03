package com.androidjson.inbox;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class category extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    CardView busi, ente, heal, scie, spor, tech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //Add from here *********
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        //To here ******

        busi = (CardView) findViewById(R.id.bus);
        ente = (CardView) findViewById(R.id.ent);
        heal = (CardView) findViewById(R.id.hea);
        scie = (CardView) findViewById(R.id.sci);
        spor = (CardView) findViewById(R.id.spo);
        tech = (CardView) findViewById(R.id.tec);

        busi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(category.this, BusAPI.class);
                startActivity(i1);
            }
        });

        ente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(category.this, EntAPI.class);
                startActivity(i2);
            }
        });

        heal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(category.this, HeaAPI.class);
                startActivity(i3);
            }
        });

        scie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(category.this, SciAPI.class);
                startActivity(i4);
            }
        });

        spor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(category.this, SpoAPI.class);
                startActivity(i5);
            }
        });

        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(category.this, TechAPI.class);
                startActivity(i6);
            }
        });
    }

    //From here also *****
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.profile :
                Toast.makeText(this,"This is profile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.aboutus :
                Toast.makeText(this,"This is Aboutus",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings :
                Toast.makeText(this,"This is settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout :
                Toast.makeText(this,"You have successfully logged out",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return false;
    }
    //To here *******
}



