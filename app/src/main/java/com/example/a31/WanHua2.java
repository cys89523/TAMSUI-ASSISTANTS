package com.example.a31;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
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

public class WanHua2 extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5;
    Context context = this;
    ListView LV1;
    Button btn2;

    int x_last = 0;
    String x_select;
    SimpleAdapter adapter;
    private static ArrayList<HashMap<String, Object>> items;
    private static ArrayList<HashMap<String, Object>> searchResults;
    private EditText editText;
    private List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_hua2);

        LV1=(ListView) findViewById(R.id.LV1wh);

       // btn2=findViewById(R.id.btn_2wh);





        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef12 = database.getReference("Users").child("wh");
        // final DatabaseReference myRef12=myRef1.child("rana");//final

        final List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();//final

       /*btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WanHua.class);
                startActivity(intent);
            }
        });*/

        myRef12.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int x_sum = (int) dataSnapshot.getChildrenCount();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    TextString user_data = ds.getValue(TextString.class);
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", ds.getKey());
                    item.put("z1", user_data.getZ1());
                    item.put("name", user_data.getName());
                    item.put("z3", user_data.getZ3());
                    item.put("z4", user_data.getZ4());
                    item.put("z5", user_data.getZ5());
                    item.put("z6",user_data.getZ6());
                    item.put("z7",user_data.getZ7());
                    items.add(item);
                    x_last = Integer.parseInt(ds.getKey());

                }
                SimpleAdapter SA1 = new SimpleAdapter(context, items, R.layout.activity_text_string, new String[]{"id", "z1", "name", "z3", "z4", "z5","z6","z7"}, new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6,R.id.text7,R.id.text8});
                LV1.setAdapter(SA1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










        //資料傳到另一列表
        LV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView t1_s = (TextView) view.findViewById(R.id.text1);
                x_select = t1_s.getText().toString();
                TextView t2_s = (TextView) view.findViewById(R.id.text2);
                // t1.setText(t2_s.getText().toString());
                TextView t3_s = (TextView) view.findViewById(R.id.text3);
                //  t2.setText(t3_s.getText().toString());
                TextView t4_s = (TextView) view.findViewById(R.id.text4);
                //   t3.setText(t4_s.getText().toString());
                TextView t5_s = (TextView) view.findViewById(R.id.text5);
                //  t4.setText(t5_s.getText().toString());
                TextView t6_s=(TextView)view.findViewById(R.id.text6);
                Intent intent=new Intent();
                // intent.putExtra("Text1",x_select);
                intent.putExtra("Text2",t2_s.getText().toString());
                intent.putExtra("Text3",t3_s.getText().toString());
                intent.putExtra("Text4",t4_s.getText().toString());
                intent.putExtra("Text5",t5_s.getText().toString());
                intent.putExtra("Text6",t6_s.getText().toString());

                intent.setClass(getApplicationContext(),WhPostMission.class);
                startActivity(intent);
            }
        });

    }

}