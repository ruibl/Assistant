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
public class AddlessonActivity extends AppCompatActivity {
    private Button al_but1;
    private Button al_but2;
    private DatabaseHelper helper;
    private EditText Lessonno;
    private EditText Lessonna;
    private EditText Lessonscore;
    private EditText LessonteachId;
    private TextView alResult;
    private String ID;
    private String TYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlesson);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接收值*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        al_but1=findViewById(R.id.al_but1);
        al_but2=findViewById(R.id.al_but2);
        Lessonno=findViewById(R.id.Lessonno);
        Lessonna=findViewById(R.id.Lessonna);
        Lessonscore=findViewById(R.id.Lessonscore);
        LessonteachId=findViewById(R.id.LessonteachId);
        LessonteachId.setText(ID);
        alResult=findViewById(R.id.alResult);
        /*点击添加按钮将课程写入数据库*/
        al_but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLesson();
            }
        });
        /*点击退出按钮退回到LessonActivity界面*/
        al_but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddlessonActivity.this,LessonActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void addLesson() {
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="Insert into Lessoninfo(Lessonno,Lessonna,Lessonscore,LessonteachId) values(?,?,?,?)";
        Log.i("Ex04","insert="+sql);
        String strNo=Lessonno.getText().toString();
        String strNa=Lessonna.getText().toString();
        String strSc=Lessonscore.getText().toString();
        String strTid=LessonteachId.getText().toString();
        db.execSQL(sql,new Object[]{strNo,strNa,strSc,strTid});
        alResult.setText("添加成功！");
    }
}
