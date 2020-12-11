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
import android.widget.Toast;

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
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select TNo from Teacher where TNo='"+t_etNo.getText().toString()+"'";
        Log.i("Ex04","query="+sql);
        Cursor cursor=db.rawQuery(sql,null);
        StringBuilder s2=new StringBuilder();
        int i = 0;
        //String Tno;
        if(cursor!=null){
            while(cursor.moveToNext()){
                s2.append(cursor.getString(cursor.getColumnIndex("TNo"))) ;
                i++;
                System.out.println(s2);
            }
        }
        if(i!=0){
            t_but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"该账号已被注册！",Toast.LENGTH_SHORT).show();
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
        else {
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
        tvResult.setText("注册成功！");
    }
}
