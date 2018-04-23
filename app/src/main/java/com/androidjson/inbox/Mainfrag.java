package com.androidjson.inbox;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mainfrag extends Fragment {
    CardView busi, ente, heal, scie, spor, tech;

    public Mainfrag() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        busi = (CardView) getView().findViewById(R.id.bus);
        ente = (CardView) getView().findViewById(R.id.ent);
        heal = (CardView) getView().findViewById(R.id.hea);
        scie = (CardView) getView().findViewById(R.id.sci);
        spor = (CardView) getView().findViewById(R.id.spo);
        tech = (CardView) getView().findViewById(R.id.tec);

        busi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getActivity(), BusAPI.class);
                startActivity(i1);
            }
        });

        ente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getActivity(), EntAPI.class);
                startActivity(i2);
            }
        });

        heal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getActivity(), HeaAPI.class);
                startActivity(i3);
            }
        });

        scie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(getActivity(), SciAPI.class);
                startActivity(i4);
            }
        });

        spor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(getActivity(), SpoAPI.class);
                startActivity(i5);
            }
        });

        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(getActivity(), TechAPI.class);
                startActivity(i6);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mainfrag, container, false);
    }

}
