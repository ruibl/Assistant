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

public class CheckHomeworkActivity extends AppCompatActivity {
    private Button CH_but1;
    private Button CH_but2;
    private Button CH_but3;
    private Button CH_but4;
    private Button CH_exit;
    private DatabaseHelper helper;
    private TextView CH_Na;
    private TextView CH_Lessonna;
    private TextView CH_Tid;
    private TextView CH_Sid;
    private EditText CH_HF;
    private EditText CH_Answer;
    private TextView CH_result;
    private String LID;/*传过来的学生的学号*/
    private String ID;
    private String TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_homework);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接受传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        LID=bd.getString("LID");/*这个学生的学号*/
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        CH_but1=findViewById(R.id.CH_but1);
        CH_but2=findViewById(R.id.CH_but2);
        CH_but3=findViewById(R.id.CH_but3);
        CH_but4=findViewById(R.id.CH_but4);
        CH_exit=findViewById(R.id.CH_exit);
        CH_Na=findViewById(R.id.CH_Na);
        CH_Lessonna=findViewById(R.id.CH_Lessonna);
        CH_Tid=findViewById(R.id.CH_Tid);
        CH_Sid=findViewById(R.id.CH_Sid);
        CH_HF=findViewById(R.id.CH_HF);
        CH_Answer=findViewById(R.id.CH_Answer);
        CH_result=findViewById(R.id.CH_result);
        CH_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CheckHomeworkActivity.this,HomeworklistActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /*老师在上个界面点击审批进入，批改完后保存更新至数据库*/
        if(TYPE.equals("教师")){
            //从数据库中调出作业表中的数据
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Homework where Sid='"+LID+"'and Tid='"+ID+"'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s3=new StringBuilder();
            int i = 0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s3.append(cursor.getString(cursor.getColumnIndex("HNa"))) ;//0
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Lessonna"))) ;//1
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("HF")));//2
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Answer"))) ;//3
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Sid"))) ;//4
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Tid")));//5
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Sright"))) ;//6
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Tright"))) ;//7
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Hscore")));//8
                    System.out.println(s3);
                    i++;
                }
            }
            String studenthomeworkinfo=s3.toString();
            String [] shk=studenthomeworkinfo.split(",");
            if(i!=0){
                CH_Na.setText(shk[0]);
                CH_Lessonna.setText(shk[1]);
                CH_Tid.setText(shk[5]);
                CH_Sid.setText(shk[4]);
                CH_HF.setText(shk[2]);
                CH_Answer.setText(shk[3]);
                /*优秀*/
                CH_but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strHscore="优秀";
                        SQLiteDatabase db1=helper.getWritableDatabase();
                        String sql1="update Homework set Hscore='"+strHscore+"',Tright='已修改' where HNa='"+CH_Na.getText().toString()+"'";
                        Log.i("Ex04","update="+sql1);
                        db1.execSQL(sql1);
                        CH_result.setText("批改成功！");
                    }
                });
                /*良好*/
                CH_but2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strHscore="良好";
                        SQLiteDatabase db2=helper.getWritableDatabase();
                        String sql2="update Homework set Hscore='"+strHscore+"',Tright='已修改' where HNa='"+CH_Na.getText().toString()+"'";
                        Log.i("Ex04","update="+sql2);
                        db2.execSQL(sql2);
                        CH_result.setText("批改成功！");
                    }
                });
                /*及格*/
                CH_but3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strHscore="及格";
                        SQLiteDatabase db3=helper.getWritableDatabase();
                        String sql3="update Homework set Hscore='"+strHscore+"',Tright='已修改' where HNa='"+CH_Na.getText().toString()+"'";
                        Log.i("Ex04","update="+sql3);
                        db3.execSQL(sql3);
                        CH_result.setText("批改成功！");
                    }
                });
                /*不及格*/
                CH_but4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strHscore="不及格";
                        SQLiteDatabase db4=helper.getWritableDatabase();
                        String sql4="update Homework set Hscore='"+strHscore+"',Tright='已修改' where HNa='"+CH_Na.getText().toString()+"'";
                        Log.i("Ex04","update="+sql4);
                        db4.execSQL(sql4);
                        CH_result.setText("批改成功！");
                    }
                });

            }
        }
    }
}
