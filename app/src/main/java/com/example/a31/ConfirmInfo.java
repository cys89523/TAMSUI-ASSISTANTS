package com.example.a31;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ConfirmInfo extends AppCompatActivity {
    private EditText ConNameC,ConAddressC,ConPhoneC;

    private TextView ConEmailC,ConUIC;
    private Button ConfirmBtn;
    private String email,password;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        ConNameC   =findViewById(R.id.ConNameC);
        ConAddressC=findViewById(R.id.ConAddressC);
        ConPhoneC  =findViewById(R.id.ConPhoneC);
        ConUIC     =findViewById(R.id.ConUIC);
        ConEmailC  =findViewById(R.id.ConEmailC);

        ConfirmBtn=findViewById(R.id.ConfirmBtn);

        fAuth =FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        user  =fAuth.getCurrentUser();

        userID=fAuth.getCurrentUser().getUid();
/*
        DocumentReference documentReference =fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable  DocumentSnapshot documentSnapshot, @Nullable  FirebaseFirestoreException e) {
                ConNameC.setText(documentSnapshot.getString("fullName"));
                ConAddressC.setText(documentSnapshot.getString("address"));
                ConPhoneC.setText(documentSnapshot.getString("phone"));
                ConUIC.setText(documentSnapshot.getString("ui"));
                ConEmailC.setText(documentSnapshot.getString("email"));

                /*Intent intent=new Intent();
                intent.setClass(getApplicationContext(),WanHua.class);
                intent.putExtra("TextName",userNameC.getText().toString());
                intent.putExtra("TextPhone",userPhoneC.getText().toString());

                startActivity(intent);

            }
        });
        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConNameC.getText().toString().isEmpty() || ConAddressC.getText().toString().isEmpty() ||
                        ConPhoneC.getText().toString().isEmpty() || ConUIC.getText().toString().isEmpty() ||
                        ConEmailC.getText().toString().isEmpty()){
                    Toast.makeText(ConfirmInfo.this, "不能空著！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email =ConEmailC.getText().toString();

                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference docRef =fStore.collection("users").document(user.getUid());
                        Map<String,Object>edited = new HashMap<>();
                        edited.put("email",email);
                        edited.put("fullName",ConNameC.getText().toString());
                        edited.put("ui",ConUIC.getText().toString());
                        edited.put("phone",ConPhoneC.getText().toString());
                        edited.put("address",ConAddressC.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ConfirmInfo.this, "資料已更新。", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                //finish();
                            }
                        });
                        //Toast.makeText(Personal_Info.this, "郵件已更新。", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Toast.makeText(ConfirmInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),WanHua.class);
                intent.putExtra("TextName",ConNameC.getText().toString());
                intent.putExtra("TextPhone",ConPhoneC.getText().toString());

                startActivity(intent);


            }

        });*/

    }
}