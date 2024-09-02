package com.example.a31;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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

public class Mainpage extends AppCompatActivity {
    Context context = this;
    Button b1, b2, b3,  btwh, btss,btxy;
    TextView t1, t2, t3, t4, t5;
    ListView LV;
    int x_last = 0;
    String x_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        btwh = findViewById(R.id.bt_wh);
        btwh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉Textview2
                Intent intent = new Intent(getApplicationContext(), WanHua.class);
                startActivity(intent);


            }
        });
        btss = findViewById(R.id.bt_ss);
        btss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉Textview2
                //Intent intent = new Intent(getApplicationContext(), Songshan.class);
                //startActivity(intent);


            }
        });
        btxy = findViewById(R.id.bt_xy);
        btxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉Textview2
                //Intent intent = new Intent(getApplicationContext(), Xinyi.class);
                //startActivity(intent);


            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*if (item.getItemId() == R.id.updateInfo){
            startActivity(new Intent(getApplicationContext(),Personal_Info.class));
        }*/
        if(item.getItemId() ==R.id.logout){
            startActivity(new Intent(getApplicationContext(),Logout.class));
        }
        return super.onOptionsItemSelected(item);

    }

}
