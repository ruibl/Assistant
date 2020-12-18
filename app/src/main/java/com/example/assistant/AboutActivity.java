package com.example.assistant;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
public class AboutActivity extends AppCompatActivity {
    private TextView A_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        A_check=findViewById(R.id.A_check);
        A_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"当前为最新版本！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
