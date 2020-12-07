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
import android.widget.Toast;

/*这是课程的列表Activity，提供给学生选课*/
public class LessonlistActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private String ID;
    private String TYPE;
    private LinearLayout LessonlistlinearLayout;
    private TableLayout lessonlisttablelayout;
    private TableRow lessonlisttablerow;
    private TextView lessonlist_top;
    private TextView lea_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessonlist);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接受来自学生课程表界面的传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        LessonlistlinearLayout=findViewById(R.id.LessonlistlinearLayout);
        lessonlisttablelayout=findViewById(R.id.lessonlisttablelayout);
        lessonlisttablerow=findViewById(R.id.lessonlisttablerow);
        lessonlist_top=findViewById(R.id.lessonlist_top);
        lea_result=findViewById(R.id.lea_result);
        if(TYPE.equals("学生")){
            lessonlist_top.setText("学生选课系统");
            //从数据库中查找课程表中是否有课程，有就列表显示在页面上，同时把已选的课程按钮变成已选
            final SQLiteDatabase db=helper.getWritableDatabase();
            final String sql="select * from Lessoninfo";
            Log.i("Ex04","query="+sql);
            final Cursor cursor=db.rawQuery(sql,null);
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
            String lessonlist=s.toString();
            final String [] ll=lessonlist.split(",");
            if(i!=0){
                for(int j=0;j<ll.length;j++){
                    TableRow tableRow=new TableRow(this);
                    lessonlisttablelayout.addView(tableRow);
                    TextView tx0=new TextView(this);
                    tableRow.addView(tx0);
                    final TextView tx1=new TextView(this);
                    tx1.setText(ll[j]);
                    tableRow.addView(tx1);
                    /*从数据库中查询看此学生是否选了此课程*/
                    SQLiteDatabase db1=helper.getWritableDatabase();
                    final String sql1="select * from Sslesson  where Lessonna='"+ll[j]+"'and SNo='"+ID+"'";/*看本选课同学是否选了此课程*/
                    Log.i("Ex04","query="+sql1);
                    final Cursor cursor1=db1.rawQuery(sql1,null);
                    final StringBuilder s1=new StringBuilder();
                    int m = 0;
                    if(cursor1!=null){
                        while(cursor1.moveToNext()){
                            s1.append(cursor1.getString(cursor1.getColumnIndex("Lessonna"))) ;
                            s1.append(",");
                            System.out.println(s1);
                            m++;
                        }
                    }
                    if(m!=0){/*这个学生已经选了这门课了*/
                        Button Bselect=new Button(this);
                        Bselect.setText("已选课");
                        tableRow.addView(Bselect);
                        Toast.makeText(getApplicationContext(),"你不能重复选课！",Toast.LENGTH_SHORT).show();
                    }
                    else{/*这个学生没有选这门课*/
                        final Button Bselect=new Button(this);
                        Bselect.setText("选课");
                        tableRow.addView(Bselect);
                        Bselect.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                /*从数据库中取出这门课程的所有数据*/
                                SQLiteDatabase db2=helper.getWritableDatabase();
                                String sql2="select * from Lessoninfo where Lessonna='"+tx1.getText().toString()+"'";
                                Log.i("Ex04","query="+sql2);
                                Cursor cursor2=db2.rawQuery(sql2,null);
                                StringBuilder s2=new StringBuilder();
                                int n = 0;
                                if(cursor2!=null){
                                    while(cursor2.moveToNext()){
                                        s2.append(cursor2.getString(cursor2.getColumnIndex("Lessonno"))) ;
                                        s2.append(",");
                                        s2.append(cursor2.getString(cursor2.getColumnIndex("Lessonna"))) ;
                                        s2.append(",");
                                        s2.append(cursor2.getString(cursor2.getColumnIndex("Lessonscore"))) ;
                                        s2.append(",");
                                        s2.append(cursor2.getString(cursor2.getColumnIndex("LessonteachId"))) ;
                                        System.out.println(s2);
                                        n++;
                                    }
                                }
                                String lessoninfo=s2.toString();
                                String [] li=lessoninfo.split(",");
                                if(n!=0){
                                    SQLiteDatabase db3=helper.getWritableDatabase();
                                    String sql3="Insert into Sslesson(SNo,Lessonno,Lessonna,LessonteachId) values(?,?,?,?)";
                                    Log.i("Ex04","insert="+sql3);
                                    String strSNo=ID;
                                    String strLNo=li[0];
                                    String strLNa=li[1];
                                    String strTid=li[3];
                                    db3.execSQL(sql3,new Object[]{strSNo,strLNo,strLNa,strTid});
                                }
                                Toast.makeText(getApplicationContext(),"选课成功！",Toast.LENGTH_SHORT).show();
                                Bselect.setText("已选课");
                            }
                        });
                    }
                    TextView tx2=new TextView(this);
                    tableRow.addView(tx2);
                }
            }
            else{
                TextView tx0=new TextView(this);
                lessonlisttablerow.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("无可选课程");
                lessonlisttablerow.addView(tx1);
                TextView tx2=new TextView(this);
                lessonlisttablerow.addView(tx2);
            }
        }
        else if(TYPE.equals("教师")){
            lessonlist_top.setText("删除选课");
            //从数据库中查找课程表中是否有课程，有就列表显示在页面上，同时把已选的课程按钮变成已选
            SQLiteDatabase db3=helper.getWritableDatabase();
            String sql3="select * from Lessoninfo where LessonteachId='"+ID+"'";
            Log.i("Ex04","query="+sql3);
            Cursor cursor3=db3.rawQuery(sql3,null);
            StringBuilder s3=new StringBuilder();
            int i = 0;
            if(cursor3!=null){
                while(cursor3.moveToNext()){
                    s3.append(cursor3.getString(cursor3.getColumnIndex("Lessonna"))) ;
                    s3.append(",");
                    System.out.println(s3);
                    i++;
                }
            }
            String Tlessonlist=s3.toString();
            String [] Tll=Tlessonlist.split(",");
            if(i!=0){/*这个老师添加了课程*/
                for(int j=0;j<Tll.length;j++){
                    TableRow tableRow=new TableRow(this);
                    lessonlisttablelayout.addView(tableRow);
                    TextView tx0=new TextView(this);
                    tableRow.addView(tx0);
                    TextView tx1=new TextView(this);
                    tx1.setText(Tll[j]);
                    tableRow.addView(tx1);
                    Button but1=new Button(this);
                    but1.setText("删除");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SQLiteDatabase db4=helper.getWritableDatabase();
                            String sql4 ="delete from Lessoninfo where LessonteachId='"+ID+"'";
                            Log.i("Ex04","delete="+sql4);
                            db4.execSQL(sql4);
                            lea_result.setText("删除成功，请退出界面重进查看效果");
                        }
                    });
                    TextView tx2=new TextView(this);
                    tableRow.addView(tx2);
                }


            }
            else{
                TableRow tableRow=new TableRow(this);
                lessonlisttablelayout.addView(tableRow);
                TextView tx0=new TextView(this);
                tableRow.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("您还未添加课程");
                tableRow.addView(tx1);
                Button but1=new Button(this);
                but1.setText("添加课程");
                tableRow.addView(but1);
                but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(LessonlistActivity.this,AddlessonActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("SID",ID);
                        bundle.putString("TYPE",TYPE);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                TextView tx2=new TextView(this);
                tableRow.addView(tx2);
            }
        }
        else if(TYPE.equals("家长")){
            lessonlist_top.setText("您的孩子所选课程");
        }

    }
}
