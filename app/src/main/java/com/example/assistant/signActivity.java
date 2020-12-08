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

public class signActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private String ID;
    private String TYPE;
    private LinearLayout signlinearLayout;
    private TableLayout signtablelayout;
    private TableRow signtablerow;
    private TextView sign_top;
    private TextView sign_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        helper=new DatabaseHelper(this,"Assitant",null,2);
        /*接收传值-账号和类型*/
        Intent receive=getIntent();
        Bundle bd=receive.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        signlinearLayout=findViewById(R.id.signlinearLayout);
        signtablelayout=findViewById(R.id.signtablelayout);
        signtablerow=findViewById(R.id.signtablerow);
        sign_top=findViewById(R.id.sign_top);
        sign_result=findViewById(R.id.sign_result);
        if(TYPE.equals("学生")){
            sign_top.setText("学生签到系统");
            /*查数据库看自己是否签到*/
            SQLiteDatabase db=helper.getWritableDatabase();
            String sql="select * from Sign where Sno='"+ID+"'and Sright='已签到'";
            Log.i("Ex04","query="+sql);
            Cursor cursor=db.rawQuery(sql,null);
            StringBuilder s=new StringBuilder();
            int i = 0;
            if(cursor!=null){
                while(cursor.moveToNext()){
                    s.append(cursor.getString(cursor.getColumnIndex("Sright"))) ;
                    System.out.println(s);
                    i++;
                }
            }
            if(i!=0){
                TextView tx0=new TextView(this);
                signtablerow.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("你已经签到");
                signtablerow.addView(tx1);
                Button but2=new Button(this);
                but2.setText("退出");
                signtablerow.addView(but2);
                but2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(signActivity.this,bottomActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("SID",ID);
                        bundle.putString("TYPE",TYPE);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                TextView tx2=new TextView(this);
                signtablerow.addView(tx2);
                sign_result.setText("当前签到人数："+i);
            }
            else{/*数据库中没有记录*/
                Button Sign_in =new Button(this);
                Sign_in.setText("签到");
                signlinearLayout.addView(Sign_in);
                Sign_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db=helper.getWritableDatabase();
                        String sql="Insert into Sign(Sno,Sright) values(?,?)";
                        Log.i("Ex04","insert="+sql);
                        String strSno=ID;
                        String strSright="已签到";
                        db.execSQL(sql,new Object[]{strSno,strSright});
                        sign_result.setText("签到成功！");
                    }
                });

            }
        }
        else if(TYPE.equals("教师")){
            sign_top.setText("已签到学生列表");
            /*查数据库看是否有学生签到*/
            SQLiteDatabase db1=helper.getWritableDatabase();
            String sql1="select * from Sign where Sright='已签到'";
            Log.i("Ex04","query="+sql1);
            Cursor cursor1=db1.rawQuery(sql1,null);
            StringBuilder s1=new StringBuilder();
            int k = 0;
            if(cursor1!=null){
                while(cursor1.moveToNext()){
                    s1.append(cursor1.getString(cursor1.getColumnIndex("Sno"))) ;
                    s1.append(",");
                    System.out.println(s1);
                    k++;
                }
            }
            String Ssignlist=s1.toString();
            String [] Sl=Ssignlist.split(",");
            if(k!=0){
                for(int j=0;j<Sl.length;j++){
                    TableRow tableRow=new TableRow(this);
                    signtablelayout.addView(tableRow);
                    TextView tx0=new TextView(this);
                    tableRow.addView(tx0);
                    final TextView tx1=new TextView(this);
                    tx1.setText(Sl[j]);
                    tableRow.addView(tx1);
                    Button but1=new Button(this);
                    but1.setText("取消签到");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SQLiteDatabase db2=helper.getWritableDatabase();
                            String sql2 ="delete from Sign where Sno='"+tx1.getText().toString()+"'";
                            Log.i("Ex04","delete="+sql2);
                            db2.execSQL(sql2);
                            Toast.makeText(getApplicationContext(),"取消成功！",Toast.LENGTH_SHORT).show();
                        }
                    });
                    TextView tx2=new TextView(this);
                    tableRow.addView(tx2);
                }
                sign_result.setText("当前签到人数："+k);
            }
            else{
                TableRow tableRow=new TableRow(this);
                signtablelayout.addView(tableRow);
                TextView tx0=new TextView(this);
                tableRow.addView(tx0);
                TextView tx1=new TextView(this);
                tx1.setText("现在还没人签到");
                tableRow.addView(tx1);
                Button but1=new Button(this);
                but1.setText("退出");
                tableRow.addView(but1);
                but1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(signActivity.this,bottomActivity.class);
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
    }
}
