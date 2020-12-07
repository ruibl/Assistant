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

public class leaveActivity extends AppCompatActivity {
    private Button leave_but1;
    private Button leave_but2;
    private DatabaseHelper helper;
    private EditText leave_edno;
    private EditText leave_edna;
    private EditText leave_edcla;
    private EditText leave_edp;
    private EditText leave_edt;
    private EditText leave_edtback;
    private EditText leave_edthing;
    //private EditText leave_edparents;
    //private EditText leave_teach;
    private TextView ltvResult;
    private String ID;
    private String LID;
    private String TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        leave_but1=findViewById(R.id.leave_but1);
        leave_but2=findViewById(R.id.leave_but2);
        leave_edno=findViewById(R.id.leave_edno);
        leave_edna=findViewById(R.id.leave_edna);
        leave_edcla=findViewById(R.id.leave_edcla);
        leave_edp=findViewById(R.id.leave_edp);
        leave_edt=findViewById(R.id.leave_edt);
        leave_edtback=findViewById(R.id.leave_edtback);
        leave_edthing=findViewById(R.id.leave_edthing);
        ltvResult=findViewById(R.id.ltvResult);

        helper=new DatabaseHelper(this,"Assitant",null,2);
        Intent in1=getIntent();
        Bundle bd=in1.getExtras();
        LID=bd.getString("LID");
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");

        if(TYPE.equals("学生")){
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
                        Bundle bundle=new Bundle();
                        bundle.putString("SID",ID);
                        bundle.putString("TYPE",TYPE);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
        }
        else if(TYPE.equals("教师")){
            //从数据库中查找此学生是否提交了请假条
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Leave where Lno='"+LID+"'and Lts='"+0+"'";
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
            String studentleaveinfo=s3.toString();
            String [] sli=studentleaveinfo.split(",");
            if(i!=0){
                leave_edno.setText(sli[0]);
                leave_edna.setText(sli[1]);
                leave_edcla.setText(sli[2]);
                leave_edp.setText(sli[3]);
                leave_edt.setText(sli[4]);
                leave_edtback.setText(sli[5]);
                leave_edthing.setText(sli[6]);
                leave_but1.setText("同意");
                leave_but2.setText("不同意");
                leave_but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db=helper.getWritableDatabase();
                        String sql="update Leave set Lts='同意' where LNo='"+LID+"'";
                        Log.i("Ex04","update="+sql);
                        db.execSQL(sql);
                        ltvResult.setText("批改成功！");
                    }
                });
                leave_but2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db=helper.getWritableDatabase();
                        String sql="update Leave set Lts='不同意' where LNo='"+LID+"'";
                        Log.i("Ex04","update="+sql);
                        db.execSQL(sql);
                        ltvResult.setText("批改成功！");
                    }
                });
            }
        }
        else{
            //从数据库中查找此学生是否提交了请假条
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Leave where Lno='"+LID+"'and Lps='"+0+"'";
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
            String studentleaveinfo=s3.toString();
            String [] sli=studentleaveinfo.split(",");
            if(i!=0){
                leave_edno.setText(sli[0]);
                leave_edna.setText(sli[1]);
                leave_edcla.setText(sli[2]);
                leave_edp.setText(sli[3]);
                leave_edt.setText(sli[4]);
                leave_edtback.setText(sli[5]);
                leave_edthing.setText(sli[6]);
                leave_but1.setText("同意");
                leave_but2.setText("不同意");
                leave_but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db=helper.getWritableDatabase();
                        String sql="update Leave set Lps='同意' where LNo='"+LID+"'";
                        Log.i("Ex04","update="+sql);
                        db.execSQL(sql);
                        ltvResult.setText("批改成功！");
                    }
                });
                leave_but2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db=helper.getWritableDatabase();
                        String sql="update Leave set Lps='不同意' where LNo='"+LID+"'";
                        Log.i("Ex04","update="+sql);
                        db.execSQL(sql);
                        ltvResult.setText("批改成功！");
                    }
                });
            }
        }
    }
    private void insertlData(){
        SQLiteDatabase db=helper.getWritableDatabase();

        String sql="Insert into Leave(Lno,Lna,Lcl,Lph,Lle,Lba,Lth,Lps,Lts) values(?,?,?,?,?,?,?,?,?)";
        Log.i("Ex04","insert="+sql);

        String Lno=leave_edno.getText().toString();
        String Lna=leave_edna.getText().toString();
        String Lcl=leave_edcla.getText().toString();
        String Lph=leave_edp.getText().toString();
        String Lle=leave_edt.getText().toString();
        String Lba=leave_edtback.getText().toString();
        String Lth=leave_edthing.getText().toString();
        String Lps="0";
        String Lts="0";


        db.execSQL(sql,new Object[]{Lno,Lna,Lcl,Lph,Lle,Lba,Lth,Lps,Lts});
        ltvResult.setText("提交成功！");
    }
}
