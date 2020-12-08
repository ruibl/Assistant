package com.example.assistant;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class learn extends Fragment {
    private String ID;
    private String TYPE;
    private TextView learn_top;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.activity_learn, container, false);
        View view=inflater.inflate(R.layout.activity_learn, container, false);
        Intent in1=getActivity().getIntent();
        Bundle bd=in1.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        /*System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ID+TYPE);*/
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        learn_top=getActivity().findViewById(R.id.learn_top);
        /*请假功能*/
        ImageButton imb_qj=getActivity().findViewById(R.id.imb_qj);
        imb_qj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(ID+TYPE);*/
                if(TYPE.equals("学生")){
                    Intent intent=new Intent(getActivity(),SleavelistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if(TYPE.equals("教师")){
                    Intent intent=new Intent(getActivity(),SleavelistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if(TYPE.equals("家长")){
                    Intent intent=new Intent(getActivity(),SleavelistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        /*课程功能*/
        ImageButton imb_kcb=getActivity().findViewById(R.id.imb_kcb);
        imb_kcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TYPE.equals("学生")){
                    Intent intent=new Intent(getActivity(),LessonActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if(TYPE.equals("教师")){
                    Intent intent=new Intent(getActivity(),LessonActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if(TYPE.equals("家长")){
                    Intent intent=new Intent(getActivity(),LessonActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        /*作业功能*/
        ImageButton imb_zy=getActivity().findViewById(R.id.imb_zy);
        imb_zy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TYPE.equals("学生")){
                    Intent intent=new Intent(getActivity(),HomeworklistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if(TYPE.equals("教师")){
                    Intent intent=new Intent(getActivity(),HomeworklistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(getActivity(),HomeworklistActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    //Toast.makeText(getActivity().getApplicationContext(),"抱歉，该版本暂不支持家长查看作业的功能，敬请期待！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*签到功能*/
        ImageButton imb_qd=getActivity().findViewById(R.id.imb_qd);
        imb_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*学生点击签到可进行签到，将自己的学号和已签到写到数据库*/
                if(TYPE.equals("学生")){
                    Intent intent=new Intent(getActivity(),signActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                /*教师可查看签到人数，和已签到名单*/
                else if(TYPE.equals("教师")){
                    Intent intent=new Intent(getActivity(),signActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(),"抱歉，该版本暂不支持家长签到的功能，敬请期待！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*阅读功能*/
        ImageButton imb_yd=getActivity().findViewById(R.id.imb_yd);
        imb_yd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ReadActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /*直播课堂*/
        ImageButton imb_wk=getActivity().findViewById(R.id.imb_wk);
        imb_wk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LiveActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("SID",ID);
                bundle.putString("TYPE",TYPE);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
