package com.example.assistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class my extends Fragment {
    private String ID;
    private String TYPE;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_my, container, false);
        Intent in1=getActivity().getIntent();
        Bundle bd=in1.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        return view;
    }
}
