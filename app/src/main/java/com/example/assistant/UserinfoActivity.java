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

public class UserinfoActivity extends AppCompatActivity {
    private String ID;
    private String TYPE;
    private DatabaseHelper helper;
    private Button U_but1;
    private Button U_but2;
    private TextView UT1;
    private TextView UT1_latter;
    private TextView UT2;
    private TextView UT3;
    private TextView UT4;
    private TextView UT5;
    private TextView UResult;
    private EditText UT2_latter;
    private EditText UT3_latter;
    private EditText UT4_latter;
    private EditText UT5_latter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接受来自学生课程表界面的传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        U_but1=findViewById(R.id.U_but1);
        U_but2=findViewById(R.id.U_but2);
        UT1=findViewById(R.id.UT1);
        UT1_latter=findViewById(R.id.UT1_latter);
        UT2=findViewById(R.id.UT2);
        UT3=findViewById(R.id.UT3);
        UT4=findViewById(R.id.UT4);
        UT5=findViewById(R.id.UT5);
        UResult=findViewById(R.id.UResult);
        UT2_latter=findViewById(R.id.UT2_latter);
        UT3_latter=findViewById(R.id.UT3_latter);
        UT4_latter=findViewById(R.id.UT4_latter);
        UT5_latter=findViewById(R.id.UT5_latter);
        if(TYPE.equals("学生")){
            /*查询学生数据库，调出数据*/
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Student where SNo='"+ID+"'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s3=new StringBuilder();
            int i=0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s3.append(cursor.getString(cursor.getColumnIndex("SNo"))) ;//0
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("SName"))) ;//1
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Sclass")));//2
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Sphone"))) ;//3
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Spwd"))) ;//4
                    System.out.println(s3);
                    i++;
                }
            }
            String studentinfo=s3.toString();
            final String [] si=studentinfo.split(",");
            if(i!=0){
                UT1.setText("学  号:");
                UT2.setText("姓  名:");
                UT3.setText("班  级");
                UT4.setText("电  话:");
                UT5.setText("密  码:");
                UT1_latter.setText(si[0]);
                UT2_latter.setText(si[1]);
                UT3_latter.setText(si[2]);
                UT4_latter.setText(si[3]);
                UT5_latter.setText(si[4]);
                U_but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db1=helper.getWritableDatabase();
                        String sql1="update Student set SName='"+si[1]+"',Sclass='"+si[2]+"',Sphone='"+si[3]+"',Spwd='"+si[4]+"' where SNo='"+ID+"'";
                        Log.i("Ex04","update="+sql1);
                        db1.execSQL(sql1);
                        UResult.setText("修改成功！");
                    }
                });
            }
            U_but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UserinfoActivity.this,SetActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else if(TYPE.equals("教师")){
            /*查询教师数据库，调出数据*/
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Teacher where TNo='"+ID+"'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s3=new StringBuilder();
            int i=0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s3.append(cursor.getString(cursor.getColumnIndex("TNo"))) ;//0
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Tpwd"))) ;//1
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("TName")));//2
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Tphone"))) ;//3
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Tclass"))) ;//4
                    System.out.println(s3);
                    i++;
                }
            }
            String teacherinfo=s3.toString();
            final String [] ti=teacherinfo.split(",");
            if(i!=0){
                UT1.setText("帐  号:");
                UT2.setText("密  码:");
                UT3.setText("姓  名:");
                UT4.setText("电  话:");
                UT5.setText("班  级:");
                UT1_latter.setText(ti[0]);
                UT2_latter.setText(ti[1]);
                UT3_latter.setText(ti[2]);
                UT4_latter.setText(ti[3]);
                UT5_latter.setText(ti[4]);
                U_but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db1=helper.getWritableDatabase();
                        String sql1="update Teacher set Tpwd='"+ti[1]+"',TName='"+ti[2]+"',Tphone='"+ti[3]+"',Tclass='"+ti[4]+"' where TNo='"+ID+"'";
                        Log.i("Ex04","update="+sql1);
                        db1.execSQL(sql1);
                        UResult.setText("修改成功！");
                    }
                });
            }
            U_but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UserinfoActivity.this,SetActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else{
            /*查询教师数据库，调出数据*/
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Parents where Pname='"+ID+"'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s3=new StringBuilder();
            int i=0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s3.append(cursor.getString(cursor.getColumnIndex("Pname"))) ;//0
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Ppwd"))) ;//1
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Pphone")));//2
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Pclass"))) ;//3
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("PSname"))) ;//4
                    System.out.println(s3);
                    i++;
                }
            }
            String teacherinfo=s3.toString();
            final String [] ti=teacherinfo.split(",");
            if(i!=0){
                UT1.setText("账    号:");
                UT2.setText("密    码:");
                UT3.setText("手机号码:");
                UT4.setText("孩子班级:");
                UT5.setText("孩子学号:");
                UT1_latter.setText(ti[0]);
                UT2_latter.setText(ti[1]);
                UT3_latter.setText(ti[2]);
                UT4_latter.setText(ti[3]);
                UT5_latter.setText(ti[4]);
                U_but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db1=helper.getWritableDatabase();
                        String sql1="update Parents set Ppwd='"+ti[1]+"',Pphone='"+ti[2]+"',Pclass='"+ti[3]+"',PSname='"+ti[4]+"' where Pname='"+ID+"'";
                        Log.i("Ex04","update="+sql1);
                        db1.execSQL(sql1);
                        UResult.setText("修改成功！");
                    }
                });
            }
            U_but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UserinfoActivity.this,SetActivity.class);
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
