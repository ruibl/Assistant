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
public class HomeworkResultActivity extends AppCompatActivity {
    private Button HR_but1;
    private Button HR_but2;
    private DatabaseHelper helper;
    private TextView HR_Na;
    private TextView HR_Lessonna;
    private TextView HR_Tid;
    private EditText HR_Sid;
    private EditText HR_HF;
    private EditText HR_Answer;
    private TextView HR_result;
    private String Lessonna;
    private String ID;
    private String TYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_result);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接受传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        Lessonna=bd.getString("Lessonna");
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        HR_but1=findViewById(R.id.HR_but1);
        HR_but2=findViewById(R.id.HR_but2);
        HR_Na=findViewById(R.id.HR_Na);
        HR_Lessonna=findViewById(R.id.HR_Lessonna);
        HR_Tid=findViewById(R.id.HR_Tid);
        HR_Sid=findViewById(R.id.HR_Sid);
        HR_HF=findViewById(R.id.HR_HF);
        HR_Answer=findViewById(R.id.HR_Answer);
        HR_result=findViewById(R.id.HR_result);
        /*学生在上个界面点击写作业进入，填写答案后点击提交，把内容更新至数据库*/
        if(TYPE.equals("学生")){
            //从数据库中调出作业表中的数据
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Homework where Lessonna='"+Lessonna+"'";
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
            String homeworkinfo=s3.toString();
            String [] hk=homeworkinfo.split(",");
            if(i!=0){
                HR_Na.setText(hk[0]);
                HR_Lessonna.setText(hk[1]);
                HR_Tid.setText(hk[5]);
                HR_Sid.setText(ID);
                HR_HF.setText(hk[2]);
                HR_but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        update();
                    }
                });
            }
            else{
                HR_result.setText("没有作业信息，请联系教师");
            }
            HR_but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(HomeworkResultActivity.this,HomeworklistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }
    private void update() {
        String strAn=HR_Answer.getText().toString();
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="update Homework set Sid='"+ID+"',Answer='"+strAn+"',Sright='已提交' where HNa='"+HR_Na.getText().toString()+"'";
        Log.i("Ex04","update="+sql);
        db.execSQL(sql);
        HR_result.setText("提交成功！");
    }
}
