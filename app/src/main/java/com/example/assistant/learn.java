package com.example.assistant;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


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

                }
                else if(TYPE.equals("教师")){

                }
                else if(TYPE.equals("家长")){

                }
            }
        });

    }


}
