package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HomeworklistActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private String ID;
    private String TYPE;
    private LinearLayout HomeworklistlinearLayout;
    private TableLayout Homeworklisttablelayout;
    private TableRow Homeworklisttablerow;
    private TextView Homeworklist_top;
    private TextView Homeworklist_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeworklist);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接受传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        HomeworklistlinearLayout=findViewById(R.id.HomeworklistlinearLayout);
        Homeworklisttablelayout=findViewById(R.id.Homeworklisttablelayout);
        Homeworklisttablerow=findViewById(R.id.Homeworklisttablerow);
        Homeworklist_top=findViewById(R.id.Homeworklist_top);
        Homeworklist_result=findViewById(R.id.Homeworklist_result);
        /*如果类型为学生，则列表展示教师留的作业*/
        if(TYPE.equals("学生")){
            Homeworklist_top.setText("作业列表");
            //从数据库中读取老师已发的作业，如果有，列表展示，有一个写作业按钮
            SQLiteDatabase db4=helper.getWritableDatabase();
            String sql4="select * from Homework where Sright='0'";
            Log.i("Ex04","query="+sql4);
            Cursor cursor4=db4.rawQuery(sql4,null);
            StringBuilder s4=new StringBuilder();
            int i = 0;
            if(cursor4!=null){
                while(cursor4.moveToNext()){
                    s4.append(cursor4.getString(cursor4.getColumnIndex("Lessonna"))) ;
                    s4.append(",");
                    System.out.println(s4);
                    i++;
                }
            }
            String S4=s4.toString();
            String [] Sview=S4.split(",");
            if(i!=0){
                for(int j=0;j<Sview.length;j++){
                    TableRow tableRow=new TableRow(this);
                    Homeworklisttablelayout.addView(tableRow);
                    TextView tx0=new TextView(this);
                    tableRow.addView(tx0);
                    final TextView tx1=new TextView(this);
                    tx1.setText(Sview[j]);
                    tableRow.addView(tx1);
                    final Button but1=new Button(this);
                    but1.setText("写作业");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(HomeworklistActivity.this,HomeworkResultActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("Lessonna",tx1.getText().toString());
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            but1.setText("已查看");
                        }
                    });
                    TextView tx2=new TextView(this);
                    tableRow.addView(tx2);
                }
            }
            /*作业表中没作业或已批改*/
            else{
                SQLiteDatabase db8=helper.getWritableDatabase();
                String sql8="select * from Homework where Tright='已修改'";
                Log.i("Ex04","query="+sql8);
                Cursor cursor8=db8.rawQuery(sql8,null);
                StringBuilder s8=new StringBuilder();
                int r = 0;
                if(cursor8!=null){
                    while(cursor8.moveToNext()){
                        s8.append(cursor8.getString(cursor8.getColumnIndex("Lessonna"))) ;
                        s8.append("：");
                        s8.append(cursor8.getString(cursor8.getColumnIndex("Hscore"))) ;
                        s8.append(",");
                        System.out.println(s8);
                        r++;
                    }
                }
                String score=s8.toString();
                final String [] sco=score.split(",");
                if(r!=0){
                    for(int a=0;a<sco.length;a++){
                        TextView tx0=new TextView(this);
                        Homeworklisttablerow.addView(tx0);
                        TextView tx1=new TextView(this);
                        tx1.setText(sco[a]);
                        Homeworklisttablerow.addView(tx1);
                        Button but2=new Button(this);
                        but2.setText("删除");
                        Homeworklisttablerow.addView(but2);
                        but2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SQLiteDatabase db9=helper.getWritableDatabase();
                                String sql9 ="delete from Homework where Sid='"+ID+"'";
                                Log.i("Ex04","delete="+sql9);
                                db9.execSQL(sql9);

                            }
                        });
                        TextView tx2=new TextView(this);
                        Homeworklisttablerow.addView(tx2);
                    }
                }
                else{
                    TextView tx0=new TextView(this);
                    Homeworklisttablerow.addView(tx0);
                    TextView tx1=new TextView(this);
                    tx1.setText("作业还没有发放");
                    Homeworklisttablerow.addView(tx1);
                    Button but2=new Button(this);
                    but2.setText("退出");
                    Homeworklisttablerow.addView(but2);
                    but2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(HomeworklistActivity.this,bottomActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    TextView tx2=new TextView(this);
                    Homeworklisttablerow.addView(tx2);
                }
            }
        }
        /*如果类型为教师，则列表展示学生提交了的作业，有一个添加作业按钮*/
        else if(TYPE.equals("教师")){
            Homeworklist_top.setText("提交作业列表");
            Button AddHomework =new Button(this);
            AddHomework.setText("添加作业");
            Homeworklisttablelayout.addView(AddHomework);
            AddHomework.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(HomeworklistActivity.this,AddHomeworkActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            //从数据库中读取学生已提交的作业，如果有，列表展示，有一个打分按钮
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Homework where Tid='"+ID+"'and Sright='已提交'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s=new StringBuilder();
            int i = 0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s.append(cursor.getString(cursor.getColumnIndex("Sid"))) ;
                    s.append(",");
                    System.out.println(s);
                    i++;
                }
            }
            String S=s.toString();
            String [] Shand=S.split(",");
            if(i!=0){/*有学生提交作业*/
                for(int j=0;j<Shand.length;j++){
                    TableRow tableRow=new TableRow(this);
                    Homeworklisttablelayout.addView(tableRow);
                    TextView tx0=new TextView(this);
                    tableRow.addView(tx0);
                    final TextView tx1=new TextView(this);
                    tx1.setText(Shand[j]);
                    tableRow.addView(tx1);
                    final Button but1=new Button(this);
                    but1.setText("打分");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(HomeworklistActivity.this,CheckHomeworkActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",tx1.getText().toString());/*这个学生的学号*/
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            but1.setText("已审批");
                        }
                    });
                    TextView tx2=new TextView(this);
                    tableRow.addView(tx2);
                }
            }
            else{/*没有学生提交作业*/
                TextView tx0=new TextView(this);
                Homeworklisttablerow.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("没有学生提交作业");
                Homeworklisttablerow.addView(tx1);
                Button but2=new Button(this);
                but2.setText("退出");
                Homeworklisttablerow.addView(but2);
                but2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(HomeworklistActivity.this,bottomActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("SID",ID);
                        bundle.putString("TYPE",TYPE);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                TextView tx2=new TextView(this);
                Homeworklisttablerow.addView(tx2);
            }
        }
        /*如果类型为家长，则可以查看学生作业的分数*/
        else if(TYPE.equals("家长")){
            Homeworklist_top.setText("孩子作业分数");
            /*从数据库中读取出自己孩子的各项作业的分数*/
            //从数据库中查找自己孩子的学号
            SQLiteDatabase db1=helper.getWritableDatabase();
            String sql1="select PSname from Parents where Pname='"+ID+"'";
            Log.i("Ex04","query="+sql1);
            Cursor cursor1=db1.rawQuery(sql1,null);
            StringBuilder s1=new StringBuilder();
            int i = 0;
            if(cursor1!=null){
                while(cursor1.moveToNext()){
                    s1.append(cursor1.getString(cursor1.getColumnIndex("PSname"))) ;
                    System.out.println(s1);
                    i++;
                }
            }
            if(i!=0){
                //从数据库中读取老师已批改的作业，如果有，列表展示，显示分数
                SQLiteDatabase db2=helper.getWritableDatabase();
                String sql2="select * from Homework where Sid='"+s1.toString()+"'and Tright='已修改'";
                Log.i("Ex04","query="+sql2);
                Cursor cursor2=db2.rawQuery(sql2,null);
                StringBuilder s2=new StringBuilder();
                int k = 0;
                if(cursor2!=null){
                    while(cursor2.moveToNext()){
                        s2.append(cursor2.getString(cursor2.getColumnIndex("Lessonna"))) ;
                        s2.append("：");
                        s2.append(cursor2.getString(cursor2.getColumnIndex("Hscore"))) ;
                        s2.append(",");
                        System.out.println(s2);
                        k++;
                    }
                }
                String ls=s2.toString();
                String [] Ls=ls.split(",");
                if(k!=0){/*孩子的作业列表显示*/
                    for(int j=0;j<Ls.length;j++){
                        TableRow tableRow=new TableRow(this);
                        Homeworklisttablelayout.addView(tableRow);
                        TextView tx0=new TextView(this);
                        tableRow.addView(tx0);
                        final TextView tx1=new TextView(this);
                        tx1.setText(Ls[j]);
                        tableRow.addView(tx1);
                        Button but1=new Button(this);
                        but1.setText("好评");
                        tableRow.addView(but1);
                        TextView tx2=new TextView(this);
                        tableRow.addView(tx2);
                    }
                }
                else{
                    TextView tx0=new TextView(this);
                    Homeworklisttablerow.addView(tx0);
                    TextView tx1=new TextView(this);
                    tx1.setText("作业还没有批改完");
                    Homeworklisttablerow.addView(tx1);
                    Button but2=new Button(this);
                    but2.setText("退出");
                    Homeworklisttablerow.addView(but2);
                    but2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(HomeworklistActivity.this,bottomActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    TextView tx2=new TextView(this);
                    Homeworklisttablerow.addView(tx2);
                }
            }
        }
    }
}
