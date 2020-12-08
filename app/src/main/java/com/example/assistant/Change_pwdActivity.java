package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Change_pwdActivity extends AppCompatActivity {
    private String ID;
    private String TYPE;
    private DatabaseHelper helper;
    private EditText CP_old;
    private EditText CP_new;
    private Button CP_enter;
    private Button CP_quit;
    private TextView CPResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接受来自学生课程表界面的传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        CP_old=findViewById(R.id.CP_old);
        CP_new=findViewById(R.id.CP_new);
        CP_enter=findViewById(R.id.CP_enter);
        CP_quit=findViewById(R.id.CP_quit);
        CPResult=findViewById(R.id.CPResult);

    }
}
