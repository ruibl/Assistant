package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class bottomActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mBtnDy1;
    private TextView mBtnDy2;
    private TextView mBtnDy3;
    /*private String ID;
    private String TYPE;*/
    FragmentManager fm;
    Fragment mfg1;
    Fragment mfg2;
    Fragment mfg3;
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main,menu);
        //得到MenuInflater对象，再调用inflate()方法就可以给当前活动创建菜单了
        return true;
        //表示允许创建的菜单显示出来，如果false就无法显示
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){//判断我们点击的是哪一个菜单项
            case R.id.set_item:  //点击设置
                Intent intent0 = new Intent(bottomActivity.this, SetActivity.class);
                startActivity(intent0);
                break;

            case R.id.code_item:  //点击我的二维码
                Intent intent=new Intent(bottomActivity.this,QR_codeActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        fm = getSupportFragmentManager();
        mBtnDy1 = findViewById(R.id.btn_dy1);
        mBtnDy2 = findViewById(R.id.btn_dy2);
        mBtnDy3 = findViewById(R.id.btn_dy3);
        mBtnDy1.setOnClickListener(this);
        mBtnDy2.setOnClickListener(this);
        mBtnDy3.setOnClickListener(this);
        Toast.makeText(getApplicationContext(),"欢迎使用一点通家校助手！",Toast.LENGTH_SHORT).show();
        /*Intent intent1=getIntent();
        Bundle bd=intent1.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");*/
        /*System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ID+TYPE);*/
        /*Bundle bundle = new Bundle();
        bundle.putString("SID",ID);
        bundle.putString("TYPE",TYPE);
        //首先有一个Fragment对象 调用这个对象的setArguments(bundle)传递数据
        System.out.println("-----------------------------------------------");
        mfg1.setArguments(bundle);*/
        //Activity传值，通过Bundle
        /*Bundle bundle = new Bundle();
        bundle.putString("SID",ID);
        bundle.putString("TYPE",TYPE);
        //首先有一个Fragment对象 调用这个对象的setArguments(bundle)传递数据
        mfg1.setArguments(bundle);
        mfg2.setArguments(bundle);
        mfg3.setArguments(bundle);*/
        //System.out.println("-----------------------------------------------");
    }
    @Override
    public void onClick(View v) {
        clearSelection();//清除按钮状态
        FragmentTransaction ts = fm.beginTransaction();
        hideFragments(ts);
        switch (v.getId()){
            case R.id.btn_dy1:
                mBtnDy1.setBackgroundColor(993300);
                if(mfg1 == null){
                    mfg1 = new Homepage();
                    ts.add(R.id.fragment_container,mfg1);
                    /*Intent intent=new Intent(bottomActivity.this, mfg1.getClass());
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                }else {
                    ts.show(mfg1);
                }
                break;
            case R.id.btn_dy2:
                mBtnDy2.setBackgroundColor(993300);
                if(mfg2 == null){
                    mfg2 = new learn();
                    ts.add(R.id.fragment_container,mfg2);
                    /*Intent intent=new Intent(bottomActivity.this, mfg2.getClass());
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                }else {
                    ts.show(mfg2);
                }
                break;
            case R.id.btn_dy3:
                mBtnDy3.setBackgroundColor(993300);
                if(mfg3 == null){
                    mfg3 = new my();
                    ts.add(R.id.fragment_container,mfg3);
                    /*Intent intent=new Intent(bottomActivity.this, mfg3.getClass());
                    Bundle bundle=new Bundle();
                    bundle.putString("SID",ID);
                    bundle.putString("TYPE",TYPE);
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                }else {
                    ts.show(mfg3);
                }
                break;
            default:
                break;
        }
        ts.commit();
    }
    //  将所有的Fragment都置为隐藏状态。
    private void hideFragments(FragmentTransaction transaction) {
        if (mfg1 != null) {
            transaction.hide(mfg1);
        }
        if (mfg2 != null) {
            transaction.hide(mfg2);
        }
        if (mfg3 != null) {
            transaction.hide(mfg3);
        }
    }
    //   清除掉所有的选中状态。
    private void clearSelection() {
        mBtnDy1.setBackgroundColor(0xffffffff);
        mBtnDy2.setBackgroundColor(0xffffffff);
        mBtnDy3.setBackgroundColor(0xffffffff);
    }
}