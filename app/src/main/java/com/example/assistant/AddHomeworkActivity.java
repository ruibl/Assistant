package com.example.assistant;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class AddHomeworkActivity extends AppCompatActivity {
    private Button H_but1;
    private Button H_but2;
    private DatabaseHelper helper;
    private EditText H_Na;
    private EditText H_Tid;
    private EditText H_Lessonna;
    private EditText H_File;
    private TextView HResult;
    private String ID;
    private String TYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接收值*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");

        H_but1=findViewById(R.id.H_but1);
        H_but2=findViewById(R.id.H_but2);
        H_Na=findViewById(R.id.H_Na);
        H_Tid=findViewById(R.id.H_Tid);
        H_Lessonna=findViewById(R.id.H_Lessonna);
        H_File=findViewById(R.id.H_File);
        HResult=findViewById(R.id.HResult);
        H_Tid.setText(ID);
        /*点击发送按钮把作业文件传到数据库*/
        H_but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHomework();
            }
        });
        /*点击退出按钮退回到作业列表界面*/
        H_but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddHomeworkActivity.this,HomeworklistActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void sendHomework() {
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="Insert into Homework(HNa,Lessonna,HF,Answer,Sid,Tid,Sright,Tright,Hscore) values(?,?,?,?,?,?,?,?,?)";
        Log.i("Ex04","insert="+sql);
        String strHNa=H_Na.getText().toString();
        String strHla=H_Lessonna.getText().toString();
        String strHF=H_File.getText().toString();
        String strAn="0";
        String strSid="0";
        String strTid=H_Tid.getText().toString();
        String strSr="0";
        String strTr="0";
        String strHscore="0";
        db.execSQL(sql,new Object[]{strHNa,strHla,strHF,strAn,strSid,strTid,strSr,strTr,strHscore});
        HResult.setText("发送成功！");
    }
}
