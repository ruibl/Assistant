package com.example.assistant;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SleavelistActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private String ID;
    private String TYPE;
    private LinearLayout linearLayout;
    private TableLayout tableLayout1;
    private TableRow tablerow1;
    private TextView sleavelist_top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleavelist);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        Intent in1=getIntent();
        Bundle bd=in1.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        /*System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ID+TYPE);*/
        linearLayout=this.findViewById(R.id.linearLayout);
        tableLayout1=this.findViewById(R.id.tablelayout1);
        tablerow1=this.findViewById(R.id.tablerow1);
        sleavelist_top=this.findViewById(R.id.sleavelist_top);

       /* if(TYPE.equals("学生")){
            sleavelist_top.setText(TYPE+"系统");
            Button bu_qingjia=new Button(this);
            bu_qingjia.setText("请假");
            linearLayout.addView(bu_qingjia);
            bu_qingjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SleavelistActivity.this,leaveActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }*/
        if(TYPE.equals("学生")){
            sleavelist_top.setText(TYPE+"请假系统");
            //从数据库中查找此学生是否提交了请假条
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Leave where Lno='"+ID+"'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s3=new StringBuilder();
            int i = 0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s3.append(cursor.getString(cursor.getColumnIndex("Lno"))) ;
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Lps"))) ;
                    s3.append(",");
                    s3.append(cursor.getString(cursor.getColumnIndex("Lts")));
                    System.out.println(s3);
                    i++;
                }
            }
            String leaveinfo=s3.toString();
            String [] li=leaveinfo.split(",");
            if(i!=0){
                if(li[1].equals("同意") && li[2].equals("同意")){
                    TextView tx0=new TextView(this);
                    tablerow1.addView(tx0);
                    TextView tx1=new TextView(this);
                    tx1.setText("你的请假条已通过审批");
                    tablerow1.addView(tx1);
                    Button but1=new Button(this);
                    but1.setText("查看");
                    tablerow1.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(SleavelistActivity.this,SleaveresultActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",ID);
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    TextView tx2=new TextView(this);
                    tablerow1.addView(tx2);
                }
                else if(li[1].equals("0") || li[2].equals("0")){
                    /*TextView tx0=new TextView(this);
                    tablerow1.addView(tx0);*/
                    TextView tx1=new TextView(this);
                    tx1.setText("你的请假条正在审批");
                    tablerow1.addView(tx1);
                    /*Button but1=new Button(this);
                    but1.setText("查看");
                    tablerow1.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(SleavelistActivity.this,SleaveresultActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });*/
                    TextView tx2=new TextView(this);
                    tablerow1.addView(tx2);
                }
                else{
                    TextView tx0=new TextView(this);
                    tablerow1.addView(tx0);
                    TextView tx1=new TextView(this);
                    tx1.setText("你的请假条未通过审批");
                    tablerow1.addView(tx1);
                    Button but1=new Button(this);
                    but1.setText("查看");
                    tablerow1.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(SleavelistActivity.this,SleaveresultActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",ID);
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    TextView tx2=new TextView(this);
                    tablerow1.addView(tx2);
                }
            }
            else{
                Button bu_qingjia=new Button(this);
                bu_qingjia.setText("请假");
                linearLayout.addView(bu_qingjia);
                bu_qingjia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(SleavelistActivity.this,leaveActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("LID",ID);
                        bundle.putString("SID",ID);
                        bundle.putString("TYPE",TYPE);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        }
        else if(TYPE.equals("教师")){
            sleavelist_top.setText("提交的请假条");
            //从数据库中查找是否有学生提交了请假条
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Leave";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s4=new StringBuilder();
            int i = 0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s4.append(cursor.getString(cursor.getColumnIndex("Lno"))) ;
                    s4.append(",");
                    System.out.println(s4);
                    i++;
                }
            }
            String leavestudentslist=s4.toString();
            final String [] lsl=leavestudentslist.split(",");
            if(i!=0){
                for(int j=0;j<lsl.length;j++){
                    TextView tx0=new TextView(this);
                    tablerow1.addView(tx0);
                    final TextView tx1=new TextView(this);
                    tx1.setText(lsl[j]);
                    tablerow1.addView(tx1);
                    final Button but1=new Button(this);
                    but1.setText("查看");
                    tablerow1.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(SleavelistActivity.this,leaveActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",tx1.getText().toString());
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            but1.setText("已查看");
                        }
                    });
                    TextView tx2=new TextView(this);
                    tablerow1.addView(tx2);
                }
            }
            else{
                TextView tx0=new TextView(this);
                tablerow1.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("无提交的请假条：");
                tablerow1.addView(tx1);
                TextView tx2=new TextView(this);
                tablerow1.addView(tx2);
            }
        }
        else{
            sleavelist_top.setText("请假审批系统——家长");
            //从数据库中查找自己孩子的学号
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select PSname from Parents where Pname='"+ID+"'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s5=new StringBuilder();
            int i = 0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s5.append(cursor.getString(cursor.getColumnIndex("PSname"))) ;
                    System.out.println(s5);
                    i++;
                }
            }
            if(i!=0){
                //从数据库中查找自己孩子的请假条
                SQLiteDatabase db1=helper.getWritableDatabase();
                String sql1="select * from Leave where Lno='"+s5.toString()+"'";
                Log.i("Ex04","query="+sql1);
                Cursor cursor1=db1.rawQuery(sql1,null);
                StringBuilder s6=new StringBuilder();
                int k = 0;
                if(cursor1!=null){
                    while(cursor1.moveToNext()){
                        s6.append(cursor1.getString(cursor1.getColumnIndex("Lno"))) ;
                        System.out.println(s6);
                        k++;
                    }
                }
                final String st6=s6.toString();
                if(k!=0){
                    TextView tx0=new TextView(this);
                    tablerow1.addView(tx0);
                    TextView tx1=new TextView(this);
                    tx1.setText("您孩子的请假条");
                    tablerow1.addView(tx1);
                    final Button but1=new Button(this);
                    but1.setText("查看");
                    tablerow1.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(SleavelistActivity.this,leaveActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",st6);
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            but1.setText("已查看");
                        }
                    });
                    TextView tx2=new TextView(this);
                    tablerow1.addView(tx2);
                }

                }
            else{
                TextView tx0=new TextView(this);
                tablerow1.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("无提交的请假条：");
                tablerow1.addView(tx1);
                TextView tx2=new TextView(this);
                tablerow1.addView(tx2);
            }
        }
    }
}