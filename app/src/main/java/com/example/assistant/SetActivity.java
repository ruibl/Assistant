package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetActivity extends AppCompatActivity {
    private String ID;
    private String TYPE;
    private TextView S_manage;
    private TextView S_pwd;
    private TextView S_help;
    private TextView S_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        /*接受来自学生课程表界面的传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        S_manage=findViewById(R.id.S_manage);
        S_pwd=findViewById(R.id.S_pwd);
        S_help=findViewById(R.id.S_help);
        S_about=findViewById(R.id.S_about);
        /*点击账号管理，跳转进入用户信息界面可查看账号的信息并修改*/
        S_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,UserinfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /*点击修改密码，跳转进入修改密码界面可更改密码*/
        S_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,Change_pwdActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /*点击帮助，跳转到帮助中心*/
        S_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,Help_centerActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /*点击关于，跳转到关于界面*/
        S_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,AboutActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
