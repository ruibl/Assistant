package com.example.assistant;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Homepage extends Fragment {
    private DatabaseHelper helper;
    private String ID;
    private String TYPE;
    private TableLayout Homepagetablelayout;
    private TableRow Homepagetablerow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.homepage, container, false);
        Intent in1=getActivity().getIntent();
        Bundle bd=in1.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        helper=new DatabaseHelper(getActivity(),"Assitant",null,2);
        Homepagetablelayout=getActivity().findViewById(R.id.Homepagetablelayout);
        Homepagetablerow=getActivity().findViewById(R.id.Homepagetablerow);
        if(TYPE.equals("学生")){
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
                    TableRow tableRow=new TableRow(getActivity());
                    Homepagetablelayout.addView(tableRow);
                    TextView tx0=new TextView(getActivity());
                    tableRow.addView(tx0);
                    TextView tx1=new TextView(getActivity());
                    tx1.setText("你的请假条已通过审批");
                    tableRow.addView(tx1);
                    Button but1=new Button(getActivity());
                    but1.setText("查看");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(),SleaveresultActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",ID);
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    TextView tx2=new TextView(getActivity());
                    tableRow.addView(tx2);
                }
                else if(li[1].equals("0") || li[2].equals("0")){
                    /*TextView tx0=new TextView(this);
                    tablerow1.addView(tx0);*/
                    TextView tx1=new TextView(getActivity());
                    tx1.setText("你的请假条正在审批");
                    Homepagetablerow.addView(tx1);
                    TextView tx2=new TextView(getActivity());
                    Homepagetablerow.addView(tx2);
                }
                else{
                    TableRow tableRow=new TableRow(getActivity());
                    Homepagetablerow.addView(tableRow);
                    TextView tx0=new TextView(getActivity());
                    tableRow.addView(tx0);
                    TextView tx1=new TextView(getActivity());
                    tx1.setText("你的请假条未通过审批");
                    tableRow.addView(tx1);
                    Button but1=new Button(getActivity());
                    but1.setText("查看");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(),SleaveresultActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",ID);
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    TextView tx2=new TextView(getActivity());
                    tableRow.addView(tx2);
                }
            }
        }
        else if(TYPE.equals("教师")){
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
                    TableRow tableRow=new TableRow(getActivity());
                    Homepagetablelayout.addView(tableRow);
                    TextView tx0=new TextView(getActivity());
                    tableRow.addView(tx0);
                    final TextView tx1=new TextView(getActivity());
                    tx1.setText(lsl[j]);
                    tableRow.addView(tx1);
                    final Button but1=new Button(getActivity());
                    but1.setText("查看");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(),leaveActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",tx1.getText().toString());
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            but1.setText("已查看");
                        }
                    });
                    TextView tx2=new TextView(getActivity());
                    tableRow.addView(tx2);
                }
            }
        }
        else{
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
                    TableRow tableRow=new TableRow(getActivity());
                    Homepagetablelayout.addView(tableRow);
                    TextView tx0=new TextView(getActivity());
                    tableRow.addView(tx0);
                    TextView tx1=new TextView(getActivity());
                    tx1.setText("您孩子的请假条");
                    tableRow.addView(tx1);
                    final Button but1=new Button(getActivity());
                    but1.setText("查看");
                    tableRow.addView(but1);
                    but1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(),leaveActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("LID",st6);
                            bundle.putString("SID",ID);
                            bundle.putString("TYPE",TYPE);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            but1.setText("已查看");
                        }
                    });
                    TextView tx2=new TextView(getActivity());
                    tableRow.addView(tx2);
                }

            }

        }
        /*点击新闻图片按钮，跳转到新闻界面*/
        ImageButton news=getActivity().findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NewsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /*点击通知按钮，跳转到校内公告*/
        ImageButton notion=getActivity().findViewById(R.id.notion);
        notion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NoticeActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
