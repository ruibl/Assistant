package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sregActivity extends AppCompatActivity {
    private Button suser_but1;
    private Button suser_but2;
    private DatabaseHelper helper;
    private EditText et_No;
    private EditText et_Na;
    private EditText et_cl;
    private EditText et_ph;
    private EditText et_pwd;
    private TextView stvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sreg);
        suser_but1=findViewById(R.id.suser_but1);
        suser_but2=findViewById(R.id.suser_but2);
        et_No=findViewById(R.id.et_No);
        et_Na=findViewById(R.id.et_Na);
        et_cl=findViewById(R.id.et_cl);
        et_ph=findViewById(R.id.et_ph);
        et_pwd=findViewById(R.id.et_pwd);
        stvResult=findViewById(R.id.stvResult);

        helper=new DatabaseHelper(this,"Assitant",null,2);
        suser_but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=helper.getWritableDatabase();
                String sql="select SNo from Student where SNo='"+et_No.getText().toString()+"'";
                Log.i("Ex04","query="+sql);
                Cursor cursor=db.rawQuery(sql,null);
                StringBuilder s1=new StringBuilder();
                int i = 0;
                //String Tno;
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        s1.append(cursor.getString(cursor.getColumnIndex("SNo"))) ;
                        i++;
                        System.out.println(s1);
                    }
                }
                if(i!=0){
                    Toast.makeText(getApplicationContext(),"该账号已被注册",Toast.LENGTH_SHORT).show();
                }
                else {
                    insertSData();
                }
            }
        });
        suser_but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(sregActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void insertSData(){
        SQLiteDatabase db=helper.getWritableDatabase();

        String sql="Insert into Student(SNo,SName,Sclass,Sphone,Spwd) values(?,?,?,?,?)";
        Log.i("Ex04","insert="+sql);

        String strSNo=et_No.getText().toString();
        String strSPw=et_Na.getText().toString();
        String strSNa=et_cl.getText().toString();
        String strSPh=et_ph.getText().toString();
        String strSPwd=et_pwd.getText().toString();
        if(strSNo.equals("")||strSPw.equals("")||strSNa.equals("")||strSPh.equals("")||strSPwd.equals("")){
            Toast.makeText(getApplicationContext(),"请填写必要信息",Toast.LENGTH_SHORT).show();
        }
        else{
            db.execSQL(sql,new Object[]{strSNo,strSPw,strSNa,strSPh,strSPwd});
            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
        }
        //stvResult.setText("注册成功！");

    }
}
