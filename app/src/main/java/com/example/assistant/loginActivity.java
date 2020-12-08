package com.example.assistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.text.method.LinkMovementMethod;
import android.database.Cursor;
import android.text.style.ClickableSpan;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class loginActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        Button b1=findViewById(R.id.but1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                et1=findViewById(R.id.et1);
                et2=findViewById(R.id.et2);

                String str1=et1.getText().toString();
                String str2=et2.getText().toString();

                Spinner spinner=findViewById(R.id.spinner);
                String sp = spinner.getSelectedItem().toString();

                if(sp.equals("学生")){

                    SQLiteDatabase db=helper.getWritableDatabase();
                    String sql="select SNo,Spwd from Student where SNo='"+str1+"' and Spwd='"+str2+"'";
                    Log.i("Ex04","query="+sql);

                    Cursor cursor=db.rawQuery(sql,null);

                    StringBuilder s1=new StringBuilder();
                    /*StringBuilder s2=new StringBuilder();*/

                    //String[][] array = new String[cursor.getCount()][10];

                    int i = 0;
                    //String Tno;
                    if(cursor!=null){
                        while(cursor.moveToNext()){
                            s1.append(cursor.getString(cursor.getColumnIndex("SNo"))) ;
                            s1.append(",");
                            s1.append(cursor.getString(cursor.getColumnIndex("Spwd")));
                            //array[i][0] = Tno;
                            //array[i][1] = pwd;
                            i++;
                            System.out.println(s1);
                            //System.out.println(array[i][1]+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        }
                    }
                    if(i!=0){
                        String sinfo=s1.toString();

                        String [] st=sinfo.split(",");
                        if(str1.equals(st[0]) && str2.equals(st[1])){
                            Intent intent=new Intent(loginActivity.this, bottomActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("SID",str1);
                            //bundle.putString("SPWD",str2);
                            bundle.putString("TYPE","学生");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        /*else{
                            Toast.makeText(getApplicationContext(),"账号或密码错误！",Toast.LENGTH_SHORT).show();
                        }*/
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"账号或密码错误！",Toast.LENGTH_SHORT).show();
                    }

                }
                else if(sp.equals("教师")){
                    SQLiteDatabase db=helper.getWritableDatabase();
                    String sql="select TNo,Tpwd from Teacher where TNo='"+str1+"' and Tpwd='"+str2+"'";
                    Log.i("Ex04","query="+sql);

                    Cursor cursor=db.rawQuery(sql,null);

                    StringBuilder s2=new StringBuilder();
                    /*StringBuilder s2=new StringBuilder();*/

                    //String[][] array = new String[cursor.getCount()][10];

                    int i = 0;
                    //String Tno;
                    if(cursor!=null){
                        while(cursor.moveToNext()){
                            s2.append(cursor.getString(cursor.getColumnIndex("TNo"))) ;
                            s2.append(",");
                            s2.append(cursor.getString(cursor.getColumnIndex("Tpwd")));
                            i++;
                            System.out.println(s2);
                        }
                    }
                    if(i!=0){
                        String tinfo=s2.toString();
                        String [] t=tinfo.split(",");
                        if(str1.equals(t[0]) && str2.equals(t[1])){
                            Intent intent=new Intent(loginActivity.this, bottomActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("SID",str1);
                            bundle.putString("TYPE","教师");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"账号或密码错误！",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    SQLiteDatabase db=helper.getWritableDatabase();
                    String sql="select Pname,Ppwd from Parents where Pname='"+str1+"' and Ppwd='"+str2+"'";
                    Log.i("Ex04","query="+sql);
                    Cursor cursor=db.rawQuery(sql,null);
                    StringBuilder s3=new StringBuilder();
                    int i = 0;
                    if(cursor!=null){
                        while(cursor.moveToNext()){
                            s3.append(cursor.getString(cursor.getColumnIndex("Pname"))) ;
                            s3.append(",");
                            s3.append(cursor.getString(cursor.getColumnIndex("Ppwd")));
                            i++;
                            System.out.println(s3);
                            //System.out.println(array[i][1]+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        }
                    }
                    if(i!=0){
                        String pinfo=s3.toString();

                        String [] p=pinfo.split(",");
                        if(str1.equals(p[0]) && str2.equals(p[1])){
                            Intent intent=new Intent(loginActivity.this, bottomActivity.class);
                            Bundle bundle=new Bundle();Toast.makeText(getApplicationContext(),"账号或密码错误！",Toast.LENGTH_SHORT).show();
                            bundle.putString("SID",str1);
                            //bundle.putString("SPWD",str2);
                            bundle.putString("TYPE","家长");
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    else {

                    }

                }
                /*while(cursor.moveToNext()){
                    s.append(cursor.getString(cursor.getColumnIndex("TNo"))+"\t");
                    s.append(cursor.getString(cursor.getColumnIndex("Tpwd"))+"\n");
                }*/
                /*for(int j=0;j<cursor.getColumnCount();j++){
                    if(array[j][0].equals(str1)){

                        if(array[j][0].equals(str1) && array[j][1].equals(str2))
                        {
                            Intent intent=new Intent(loginActivity.this, learnActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"密码错误！",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"找不到您的信息，请先注册",Toast.LENGTH_SHORT).show();
                    }

                }*/

            }
        });

        Button b2=findViewById(R.id.but2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


        //以下代码写在onCreate（）方法当中　　　　
        TextView textView1=findViewById(R.id.reg);
        String text1="注册";
        SpannableString spannableString1=new SpannableString(text1);

        spannableString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {

                Spinner spinner=findViewById(R.id.spinner);
                String sp = spinner.getSelectedItem().toString();

                if(sp.equals("学生")){
                    Intent intent=new Intent(loginActivity.this,sregActivity.class);
                    startActivity(intent);
                }
                else if(sp.equals("教师")){
                    Intent intent=new Intent(loginActivity.this,tregActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(loginActivity.this,pregActivity.class);
                    startActivity(intent);
                }


            }
        }, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView1.setText(spannableString1);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());

    }



}


