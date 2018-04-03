package com.androidjson.inbox;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class category extends AppCompatActivity {

    CardView busi, ente, heal, scie, spor, tech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

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
}



