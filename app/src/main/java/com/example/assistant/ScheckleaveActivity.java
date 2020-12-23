package com.example.assistant;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
public class ScheckleaveActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private String ID;
    private String TYPE;
    private LinearLayout Scheckleave_linerlayout;
    private TableLayout Scheckleave_tablelayout1;
    private TableRow Scheckleave_tablerow1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheckleave);
        Intent in1=getIntent();
        Bundle bd=in1.getExtras();
        ID=bd.getString("SID");
        TYPE=bd.getString("TYPE");
        Scheckleave_linerlayout=this.findViewById(R.id.Scheckleave_linerlayout);
        Scheckleave_tablelayout1=this.findViewById(R.id.Scheckleave_tablelayout1);
        Scheckleave_tablerow1=this.findViewById(R.id.Scheckleave_tablerow1);
    }
}
