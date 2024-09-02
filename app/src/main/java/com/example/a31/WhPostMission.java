package com.example.a31;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WhPostMission extends AppCompatActivity {

    Button bt11;
    Context context=this;
    Button b1,b2,b3,bt2;
    TextView t1,t2,t3,t4,t5;
    ListView LV;
    int x_last=0;
    String x_select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whpostmission);
        String text1=getIntent().getStringExtra("Text2");
        String text2=getIntent().getStringExtra("Text3");
        String text3=getIntent().getStringExtra("Text4");
        String text4=getIntent().getStringExtra("Text5");
        String text5=getIntent().getStringExtra("Text6");

        TextView TV=(TextView)findViewById(R.id.TextView111);
        TV.setText(text1);
        TextView TV2=(TextView)findViewById(R.id.TextView222);
        TV2.setText(text2);
        TextView TV3=(TextView)findViewById(R.id.TextView333);
        TV3.setText(text3);
        TextView TV4=(TextView)findViewById(R.id.TextView444);
        TV4.setText(text4);
        TextView TV5=(TextView)findViewById(R.id.TextView555);
        TV5.setText(text5);


        bt11 = (Button) findViewById(R.id.button11);
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉Textview2
                Intent intent = new Intent(getApplicationContext(),WanHua2.class);
                startActivity(intent);


            }
        });


    }
}