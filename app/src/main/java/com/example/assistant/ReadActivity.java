package com.example.assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ReadActivity extends AppCompatActivity {
    private WebView R_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        R_web = findViewById(R.id.R_web);
        R_web.loadUrl("https://www.qidian.com/");
        R_web.getSettings().setJavaScriptEnabled(true);
        R_web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

    }
}
