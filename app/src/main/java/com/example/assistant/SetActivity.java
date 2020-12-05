package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetActivity extends AppCompatActivity {
    private Button t_but1;
    private Button t_but2;
    private DatabaseHelper helper;
    private EditText t_etNo;
    private EditText t_etNa;
    private EditText t_etPh;
    private EditText t_etPw;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
    }
}
