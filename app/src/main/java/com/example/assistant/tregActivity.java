package com.example.assistant;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class tregActivity extends AppCompatActivity {
    private Button t_but1;
    private Button t_but2;
    private DatabaseHelper helper;
    private EditText t_etNo;
    private EditText t_etNa;
    private EditText t_etPh;
    private EditText t_etPw;
    private EditText t_etcl;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treg);
        t_but1=findViewById(R.id.t_but1);
        t_but2=findViewById(R.id.t_but2);
        t_etNo=findViewById(R.id.t_etNo);
        t_etPw=findViewById(R.id.t_etPw);
        t_etNa=findViewById(R.id.t_etNa);
        t_etPh=findViewById(R.id.t_etPh);
        t_etcl=findViewById(R.id.t_etcl);
        tvResult=findViewById(R.id.tvResult);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        t_but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        t_but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(tregActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void insertData(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="Insert into Teacher(TNo,Tpwd,TName,Tphone,Tclass) values(?,?,?,?,?)";
        Log.i("Ex04","insert="+sql);

        String strNo=t_etNo.getText().toString();
        String strPw=t_etPw.getText().toString();
        String strNa=t_etNa.getText().toString();
        String strPh=t_etPh.getText().toString();
        String strcl=t_etcl.getText().toString();


        db.execSQL(sql,new Object[]{strNo,strPw,strNa,strPh,strcl});

        /*db.execSQL(sql,new Object[]{strNa});
        db.execSQL(sql,new Object[]{strPh});
        db.execSQL(sql,new Object[]{strPw});*/
        tvResult.setText("注册成功！");
    }


}
