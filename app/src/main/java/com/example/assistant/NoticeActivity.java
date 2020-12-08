package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NoticeActivity extends AppCompatActivity {
    private WebView No_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        No_web = findViewById(R.id.No_web);
        No_web.loadUrl("http://www.stdu.edu.cn/component/feedex/?Itemid=168");
        No_web.getSettings().setJavaScriptEnabled(true);
        No_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }
}
