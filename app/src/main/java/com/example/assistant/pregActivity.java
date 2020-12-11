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

public class pregActivity extends AppCompatActivity {
    private Button p_but1;
    private Button p_but2;
    private DatabaseHelper helper;
    private EditText name_ed;
    private EditText pwd_ed;
    private EditText phone_ed;
    private EditText cla_ed;
    private EditText cname_ed;
    private TextView ptvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preg);
        p_but1=findViewById(R.id.p_but1);
        p_but2=findViewById(R.id.p_but2);
        name_ed=findViewById(R.id.name_ed);
        pwd_ed=findViewById(R.id.pwd_ed);
        phone_ed=findViewById(R.id.phone_ed);
        cla_ed=findViewById(R.id.cla_ed);
        cname_ed=findViewById(R.id.cname_ed);
        ptvResult=findViewById(R.id.ptvResult);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        p_but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=helper.getWritableDatabase();
                String sql="select Pname from Parents where Pname='"+name_ed.getText().toString()+"'";
                Log.i("Ex04","query="+sql);
                Cursor cursor=db.rawQuery(sql,null);
                StringBuilder s3=new StringBuilder();
                int i = 0;
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        s3.append(cursor.getString(cursor.getColumnIndex("Pname"))) ;
                        i++;
                        System.out.println(s3);
                    }
                }
                if(i!=0){
                    Toast.makeText(getApplicationContext(),"该账号已被注册！",Toast.LENGTH_SHORT).show();
                }
                else {
                    insertData();
                }
            }
        });
        p_but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(pregActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void insertData(){
        SQLiteDatabase db=helper.getWritableDatabase();

        String sql="Insert into Parents(Pname,Ppwd,Pphone,Pclass,PSname) values(?,?,?,?,?)";
        Log.i("Ex04","insert="+sql);

        String strSNa=name_ed.getText().toString();
        String strSPw=pwd_ed.getText().toString();
        String strSPh=phone_ed.getText().toString();
        String strSCl=cla_ed.getText().toString();
        String strSCna=cname_ed.getText().toString();


        db.execSQL(sql,new Object[]{strSNa,strSPw,strSPh,strSCl,strSCna});

        /*db.execSQL(sql,new Object[]{strNa});
        db.execSQL(sql,new Object[]{strPh});
        db.execSQL(sql,new Object[]{strPw});*/
        //ptvResult.setText("注册成功！");
        Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
    }
}
