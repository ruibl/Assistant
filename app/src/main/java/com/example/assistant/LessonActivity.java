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

public class LessonActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private String ID;
    private String TYPE;
    private LinearLayout LessonlinearLayout;
    private TableLayout Lessontablelayout;
    private TableRow Lessontablerow;
    private TextView sLesson_top;
    private TextView laresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接受来自学习界面的传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        LessonlinearLayout=findViewById(R.id.LessonlinearLayout);
        Lessontablelayout=findViewById(R.id.Lessontablelayout);
        Lessontablerow=findViewById(R.id.Lessontablerow);
        sLesson_top=findViewById(R.id.sLesson_top);
        laresult=findViewById(R.id.laresult);
        /*学生课程表界面，提供选课和退选功能*/
        if(TYPE.equals("学生")){
            sLesson_top.setText("学生课程系统");
            Button SelectLensson =new Button(this);
            SelectLensson.setText("添加新的课程");
            LessonlinearLayout.addView(SelectLensson);
            SelectLensson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(LessonActivity.this,LessonlistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            //列出本学生已选的课程列表显示
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Sslesson where SNo='"+ID+"'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s=new StringBuilder();
            int i = 0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s.append(cursor.getString(cursor.getColumnIndex("Lessonna"))) ;
                    s.append(",");
                    System.out.println(s);
                    i++;
                }
            }
            String studentlesssonlist=s.toString();
            final String [] sll=studentlesssonlist.split(",");
            if(i!=0){/*学生有选课*/
                for(int j=0;j<sll.length;j++){
                    TableRow tableRow=new TableRow(this);
                    Lessontablelayout.addView(tableRow);
                    TextView tx0=new TextView(this);
                    tableRow.addView(tx0);
                    final TextView tx1=new TextView(this);
                    tx1.setText(sll[j]);
                    tableRow.addView(tx1);
                    Button but1=new Button(this);
                    but1.setText("退课");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SQLiteDatabase db1=helper.getWritableDatabase();
                            String sql1 ="delete from Sslesson where Lessonna='"+tx1.getText().toString()+"'and SNo='"+ID+"'";
                            Log.i("Ex04","delete="+sql1);
                            db1.execSQL(sql1);
                            laresult.setText("退课成功，请退出界面重进查看效果");
                        }
                    });
                    TextView tx2=new TextView(this);
                    tableRow.addView(tx2);
                }
            }
            else{/*学生无选课*/
                TextView tx0=new TextView(this);
                Lessontablerow.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("你还没有选课！");
                Lessontablerow.addView(tx1);
                Button but2=new Button(this);
                but2.setText("退出");
                Lessontablerow.addView(but2);
                but2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(LessonActivity.this,bottomActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("SID",ID);
                        bundle.putString("TYPE",TYPE);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                TextView tx2=new TextView(this);
                Lessontablerow.addView(tx2);
            }
        }
        /*教师课程功能，可查看自己教授的课程，可添加课程*/
        else if(TYPE.equals("教师")){
            sLesson_top.setText("教师课程系统");
            Button AddLensson =new Button(this);
            AddLensson.setText("添加课程");
            LessonlinearLayout.addView(AddLensson);
            Button DeleteLensson =new Button(this);
            DeleteLensson.setText("删除课程");
            LessonlinearLayout.addView(DeleteLensson);
            AddLensson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(LessonActivity.this,AddlessonActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            DeleteLensson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(LessonActivity.this,LessonlistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            //列出选了本教师课程的学生学号
            SQLiteDatabase db2=helper.getWritableDatabase();
            String sql2="select * from Sslesson where LessonteachId='"+ID+"'";
            Log.i("Ex04","query="+sql2);
            Cursor cursor2=db2.rawQuery(sql2,null);
            StringBuilder s2=new StringBuilder();
            int i = 0;
            if(cursor2!=null){
                while(cursor2.moveToNext()){
                    s2.append(cursor2.getString(cursor2.getColumnIndex("SNo"))) ;
                    s2.append(",");
                    System.out.println(s2);
                    i++;
                }
            }
            String studentSelectedlist=s2.toString();
            String [] sllt=studentSelectedlist.split(",");
            if(i!=0){/*有学生选了本教师的课，列表显示*/
                for(int j=0;j<sllt.length;j++){
                    TableRow tableRow=new TableRow(this);
                    Lessontablelayout.addView(tableRow);
                    TextView tx0=new TextView(this);
                    tableRow.addView(tx0);
                    final TextView tx1=new TextView(this);
                    tx1.setText(sllt[j]);
                    tableRow.addView(tx1);
                    Button but1=new Button(this);
                    but1.setText("踢出");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SQLiteDatabase db3=helper.getWritableDatabase();
                            String sql3 ="delete from Sslesson where SNo='"+tx1.getText().toString()+"'and LessonteachId='"+ID+"'";
                            Log.i("Ex04","delete="+sql3);
                            db3.execSQL(sql3);
                            laresult.setText("踢出成功，请退出界面重进查看效果");
                        }
                    });
                    TextView tx2=new TextView(this);
                    tableRow.addView(tx2);
                }
            }
            else{/*学生无选课*/
                TextView tx0=new TextView(this);
                Lessontablerow.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("没有学生选您的课！");
                Lessontablerow.addView(tx1);
                Button but2=new Button(this);
                but2.setText("退出");
                Lessontablerow.addView(but2);
                but2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(LessonActivity.this,bottomActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("SID",ID);
                        bundle.putString("TYPE",TYPE);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                TextView tx2=new TextView(this);
                Lessontablerow.addView(tx2);
            }
        }
        /*仅提供查看自己孩子所选课程*/
        else{
            sLesson_top.setText("家长查看选课系统");
            //找出自己孩子的学号
            SQLiteDatabase db5=helper.getWritableDatabase();
            String sql5="select * from Parents where Pname='"+ID+"'";
            Log.i("Ex04","query="+sql5);
            Cursor cursor5=db5.rawQuery(sql5,null);
            StringBuilder s5=new StringBuilder();
            int i = 0;
            if(cursor5!=null){
                while(cursor5.moveToNext()){
                    s5.append(cursor5.getString(cursor5.getColumnIndex("PSname"))) ;
                    System.out.println(s5);
                    i++;
                }
            }
            if(i!=0){
                String Sno=s5.toString();
                //列出孩子所选课程，只能查看
                SQLiteDatabase db6=helper.getWritableDatabase();
                String sql6="select * from Sslesson where SNo='"+Sno+"'";
                Log.i("Ex04","query="+sql6);
                Cursor cursor6=db6.rawQuery(sql6,null);
                StringBuilder s6=new StringBuilder();
                int t = 0;
                if(cursor6!=null){
                    while(cursor6.moveToNext()){
                        s6.append(cursor6.getString(cursor6.getColumnIndex("Lessonna"))) ;
                        s6.append(",");
                        System.out.println(s6);
                        t++;
                    }
                }
                String Pviewlist=s6.toString();
                String [] Plt=Pviewlist.split(",");
                if(i!=0){/*孩子选的课列表显示*/
                    for(int j=0;j<Plt.length;j++){
                        TableRow tableRow=new TableRow(this);
                        Lessontablelayout.addView(tableRow);
                        TextView tx0=new TextView(this);
                        tableRow.addView(tx0);
                        final TextView tx1=new TextView(this);
                        tx1.setText(Plt[j]);
                        tableRow.addView(tx1);
                        Button but1=new Button(this);
                        but1.setText("好评");
                        tableRow.addView(but1);
                        TextView tx2=new TextView(this);
                        tableRow.addView(tx2);
                    }
                }
                else{/*学生无选课*/
                    TextView tx0=new TextView(this);
                    Lessontablerow.addView(tx0);
                    TextView tx1=new TextView(this);
                    tx1.setText("您的孩子还没有选课");
                    Lessontablerow.addView(tx1);
                    Button but2=new Button(this);
                    but2.setText("退出");
                    Lessontablerow.addView(but2);
                    but2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(LessonActivity.this,bottomActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    TextView tx2=new TextView(this);
                    Lessontablerow.addView(tx2);
                }

            }
        }
    }
}
