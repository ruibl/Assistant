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
        if(TYPE.equals("学生")){
            CP_enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*查询学生数据库，调出密码*/
                    SQLiteDatabase db=helper.getWritableDatabase();
                    String sql="select * from Student where SNo='"+ID+"'";
                    Log.i("Ex04","query="+sql);
                    Cursor cursor=db.rawQuery(sql,null);
                    StringBuilder s3=new StringBuilder();
                    if(cursor!=null){
                        while(cursor.moveToNext()){
                            s3.append(cursor.getString(cursor.getColumnIndex("Spwd"))) ;//0
                            System.out.println(s3);
                        }
                    }
                    if(CP_old.getText().toString().equals(s3.toString())){

                        SQLiteDatabase db1=helper.getWritableDatabase();
                        String sql1="update Student set Spwd='"+CP_new.getText().toString()+"' where SNo='"+ID+"'";
                        Log.i("Ex04","update="+sql1);
                        db1.execSQL(sql1);
                        CPResult.setText("修改成功！");
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"原密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            CP_quit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Change_pwdActivity.this,SetActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }
        else if(TYPE.equals("教师")){
            CP_enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*查询教师数据库，调出密码*/
                    SQLiteDatabase db=helper.getWritableDatabase();
                    String sql="select * from Teacher where TNo='"+ID+"'";
                    Log.i("Ex04","query="+sql);
                    Cursor cursor=db.rawQuery(sql,null);
                    StringBuilder s3=new StringBuilder();
                    if(cursor!=null){
                        while(cursor.moveToNext()){
                            s3.append(cursor.getString(cursor.getColumnIndex("Tpwd"))) ;//0
                            System.out.println(s3);
                        }
                    }
                    if(CP_old.getText().toString().equals(s3.toString())){
                        SQLiteDatabase db1=helper.getWritableDatabase();
                        String sql1="update Teacher set Tpwd='"+CP_new.getText().toString()+"' where TNo='"+ID+"'";
                        Log.i("Ex04","update="+sql1);
                        db1.execSQL(sql1);
                        CPResult.setText("修改成功！");
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"原密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            CP_quit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Change_pwdActivity.this,SetActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


        }
        else{
            CP_enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*查询学生数据库，调出密码*/
                    SQLiteDatabase db=helper.getWritableDatabase();
                    String sql="select * from Parents where Pname='"+ID+"'";
                    Log.i("Ex04","query="+sql);
                    Cursor cursor=db.rawQuery(sql,null);
                    StringBuilder s3=new StringBuilder();
                    if(cursor!=null){
                        while(cursor.moveToNext()){
                            s3.append(cursor.getString(cursor.getColumnIndex("Ppwd"))) ;//0
                            System.out.println(s3);
                        }
                    }
                    if(CP_old.getText().toString().equals(s3.toString())){
                        SQLiteDatabase db1=helper.getWritableDatabase();
                        String sql1="update Parents set Ppwd='"+CP_new.getText().toString()+"' where Pname='"+ID+"'";
                        Log.i("Ex04","update="+sql1);
                        db1.execSQL(sql1);
                        CPResult.setText("修改成功！");
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"原密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            CP_quit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Change_pwdActivity.this,SetActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


        }

    }
}
