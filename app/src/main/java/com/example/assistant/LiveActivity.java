package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LiveActivity extends AppCompatActivity {
    private WebView L_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        L_web = findViewById(R.id.L_web);
        L_web.loadUrl("https://www.bilibili.com/");
        L_web.getSettings().setJavaScriptEnabled(true);
        L_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }
}
