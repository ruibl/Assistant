package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class SleaveresultActivity extends AppCompatActivity {
    private String ID;
    private String LID;
    private String TYPE;
    private DatabaseHelper helper;
    private EditText result_no;
    private EditText result_na;
    private EditText result_cl;
    private EditText result_ph;
    private EditText result_st;
    private EditText result_bt;
    private EditText result_th;
    private EditText result_pt;
    private EditText result_tt;
    private Button result_but1;
    //private Button result_but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleaveresult);
        Intent in1=getIntent();
        Bundle bd=in1.getExtras();
        LID=bd.getString("LID");
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        result_no=findViewById(R.id.result_no);
        result_na=findViewById(R.id.result_na);
        result_cl=findViewById(R.id.result_cl);
        result_ph=findViewById(R.id.result_ph);
        result_st=findViewById(R.id.result_st);
        result_bt=findViewById(R.id.result_bt);
        result_th=findViewById(R.id.result_th);
        result_pt=findViewById(R.id.result_pt);
        result_tt=findViewById(R.id.result_tt);
        result_but1=findViewById(R.id.result_but1);
        //result_but2=findViewById(R.id.result_but2);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*if(TYPE.equals("学生")){
            leave_but1.setText("提交");
            leave_but2.setText("退出");
            leave_but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertlData();
                }
            });
            leave_but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(leaveActivity.this,bottomActivity.class);
                    startActivity(intent);
                }
            });
        }
        else if(TYPE.equals("教师")){
            leave_but1.setText("同意");
            leave_but2.setText("不同意");
            leave_but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db=helper.getWritableDatabase();
                    String sql="update Leave set Lps='同意' where LNo='"+leave_edno.getText().toString()+"'";
                    Log.i("Ex04","update="+sql);
                    db.execSQL(sql);
                    ltvResult.setText("批改成功！");
                }
            });
            leave_but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db=helper.getWritableDatabase();
                    String sql="update Leave set Lps='不同意' where LNo='"+leave_edno.getText().toString()+"'";
                    Log.i("Ex04","update="+sql);
                    db.execSQL(sql);
                    ltvResult.setText("批改成功！");
                }
            });
        }
        else{


        }*/
        //从数据库中查找此学生是否提交了请假条
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="select * from Leave where Lno='"+LID+"'";
        Log.i("Ex04","query="+sql);
        Cursor cursor=db.rawQuery(sql,null);
        StringBuilder s3=new StringBuilder();
        int i = 0;
        if(cursor!=null){
            while(cursor.moveToNext()){
                s3.append(cursor.getString(cursor.getColumnIndex("Lno"))) ;//0
                s3.append(",");
                s3.append(cursor.getString(cursor.getColumnIndex("Lna"))) ;//1
                s3.append(",");
                s3.append(cursor.getString(cursor.getColumnIndex("Lcl")));//2
                s3.append(",");
                s3.append(cursor.getString(cursor.getColumnIndex("Lph"))) ;//3
                s3.append(",");
                s3.append(cursor.getString(cursor.getColumnIndex("Lle"))) ;//4
                s3.append(",");
                s3.append(cursor.getString(cursor.getColumnIndex("Lba")));//5
                s3.append(",");
                s3.append(cursor.getString(cursor.getColumnIndex("Lth"))) ;//6
                s3.append(",");
                s3.append(cursor.getString(cursor.getColumnIndex("Lps"))) ;//7
                s3.append(",");
                s3.append(cursor.getString(cursor.getColumnIndex("Lts")));//8
                System.out.println(s3);
                i++;
            }
        }
        final String studentleaveinfo=s3.toString();
        String [] sli=studentleaveinfo.split(",");
        if(i!=0){
            result_no.setText(sli[0]);
            result_na.setText(sli[1]);
            result_cl.setText(sli[2]);
            result_ph.setText(sli[3]);
            result_st.setText(sli[4]);
            result_bt.setText(sli[5]);
            result_th.setText(sli[6]);
            result_pt.setText(sli[7]);
            result_tt.setText(sli[8]);
            result_but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save(studentleaveinfo);
                    SQLiteDatabase db1=helper.getWritableDatabase();
                    String sql1 ="delete from Leave where Lno='"+LID+"'";
                    Log.i("Ex04","delete="+sql1);
                    db1.execSQL(sql1);
                    Intent intent=new Intent(SleaveresultActivity.this,SleavelistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }
    }
    public void save(String inputText){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("请假条", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
