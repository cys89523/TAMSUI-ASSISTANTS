package com.example.a31;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WanHua extends AppCompatActivity {
    Context context=this;
    Button b1,b2,b3,bt2,btwh;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    ListView LV;
    int x_last=0;
    String x_select,l2;

    FirebaseFirestore fStore;
    String userID;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wan_hua);
        b1=(Button)findViewById(R.id.buttonwh);
        b2=(Button)findViewById(R.id.button2wh);
        b3=(Button)findViewById(R.id.button3wh);
        bt2 = (Button) findViewById(R.id.bt_2wh);

        Intent intent=getIntent();
        String text1=intent.getStringExtra("TextName");
        //String text2=getIntent().getStringExtra("TextPhone");
        TextView t9=findViewById(R.id.textView6a);
        t9.setText(text1);

       // TextView t0=findViewById(R.id.textV3);
       // t0.setText(text2);

        //fStore=FirebaseFirestore.getInstance();

        //userID=fAuth.getCurrentUser().getUid();



        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳轉Textview2
                Intent intent = new Intent(getApplicationContext(), WanHua2.class);
                startActivity(intent);


            }
        });

        /*DocumentReference documentReference =fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                l2.
                        //setText(documentSnapshot.getString("fullName"));
            }
        });*/


        t1=(TextView) findViewById(R.id.editTextwh);
        t2=(TextView) findViewById(R.id.editText2wh);
        t3=(TextView) findViewById(R.id.editText3wh);
        t4=(TextView) findViewById(R.id.editText4wh);
        t5=(TextView) findViewById(R.id.editText5wh);
        t6=(TextView) findViewById(R.id.textView6a);
        t7=(TextView) findViewById(R.id.tv_b);

        LV=(ListView) findViewById(R.id.LVwh);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef=database.getReference( "Users").child("wh");//data_text

        // final DatabaseReference myRef=myRef1.child("rana");

        firebase_select(myRef);//1
        //新增
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x_last+=1;
                myRef.child(String.valueOf(x_last)).setValue(new TextString(t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),t4.getText().toString(),t5.getText().toString(),t6.getText().toString()," "));//1
                firebase_select(myRef);//1


            }
        });
        //修改
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(x_select).setValue(new TextString(t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),t4.getText().toString(),t5.getText().toString(),t6.getText().toString(),t7.getText().toString()));
                firebase_select(myRef);
            }
        });
        //刪除
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(x_select).removeValue();
                firebase_select(myRef);

            }
        });
        //典及顯示資訊
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t1_s=(TextView)view.findViewById(R.id.text1);
                x_select=t1_s.getText().toString();
                TextView t2_s=(TextView)view.findViewById(R.id.text2);
                t1.setText(t2_s.getText().toString());
                TextView t3_s=(TextView)view.findViewById(R.id.text3);
                t2.setText(t3_s.getText().toString());
                TextView t4_s=(TextView)view.findViewById(R.id.text4);
                t3.setText(t4_s.getText().toString());
                TextView t5_s=(TextView)view.findViewById(R.id.text5);
                t4.setText(t5_s.getText().toString());
                TextView t6_s=(TextView)view.findViewById(R.id.text6);
                t5.setText(t6_s.getText().toString());
                TextView t7_s=(TextView)view.findViewById(R.id.text7);
                t6.setText(t7_s.getText().toString());

            }
        });


    }

    private void firebase_select(DatabaseReference db) {

        final List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int x_sum=(int)dataSnapshot.getChildrenCount();

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    TextString user_data=ds.getValue(TextString.class);
                    Map<String,Object> item= new HashMap<String, Object>();
                    item.put("id",ds.getKey());
                    item.put("z1",user_data.getZ1());
                    item.put("name",user_data.getName());
                    item.put("z3",user_data.getZ3());
                    item.put("z4",user_data.getZ4());
                    item.put("z5",user_data.getZ5());
                    item.put("z6",user_data.getZ6());
                    item.put("z7",user_data.getZ7());

                    items.add(item);
                    x_last=Integer.parseInt(ds.getKey());

                }

                BaseAdapter SA=new SimpleAdapter(context,items,R.layout.activity_text_string,new String[]{"id","z1","name","z3","z4","z5","z6","z7"},new int[]{R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7,R.id.text8});
                LV.setAdapter(SA);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}