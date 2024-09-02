package com.example.a31;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class Personal_Info extends AppCompatActivity {
    private EditText userNameC,userAddressC,userPhoneC,selfIntroC;

    private TextView userEmailC,userUIC;
    private Button renewDataBtn,confirmBtn,missionBoard;
    private String email,password;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    FirebaseUser user;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        userNameC   =findViewById(R.id.userNameC);
        userAddressC=findViewById(R.id.userAddressC);
        userPhoneC  =findViewById(R.id.userPhoneC);
        userUIC     =findViewById(R.id.userUIC);
        userEmailC  =findViewById(R.id.userEmailC);
        selfIntroC  =findViewById(R.id.selfIntroC);

        renewDataBtn=findViewById(R.id.renewDataBtn);
        confirmBtn  =findViewById(R.id.confirmBtn);
        missionBoard=findViewById(R.id.missionBoard);

        fAuth =FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        user  =fAuth.getCurrentUser();

        userID=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference =fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable  DocumentSnapshot documentSnapshot, @Nullable  FirebaseFirestoreException e) {
                userNameC.setText(documentSnapshot.getString("fullName"));
                userAddressC.setText(documentSnapshot.getString("address"));
                userPhoneC.setText(documentSnapshot.getString("phone"));
                userUIC.setText(documentSnapshot.getString("ui"));
                userEmailC.setText(documentSnapshot.getString("email"));
                selfIntroC.setText(documentSnapshot.getString("intro"));

                Intent intent=new Intent();

                //intent.putExtra("TextName",userNameC.getText().toString());
               // intent.putExtra("textPhone",userPhoneC.getText().toString());
               // intent.setClass(getApplicationContext(),WanHua.class);


                //startActivity(intent);
            }
        });
        renewDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userNameC.getText().toString().isEmpty() || userAddressC.getText().toString().isEmpty() ||
                    userPhoneC.getText().toString().isEmpty() || userUIC.getText().toString().isEmpty() ||
                    userEmailC.getText().toString().isEmpty()){
                    Toast.makeText(Personal_Info.this, "不能空著！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email =userEmailC.getText().toString();

                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference docRef =fStore.collection("users").document(user.getUid());
                        Map<String,Object>edited = new HashMap<>();
                        edited.put("email",email);
                        edited.put("fullName",userNameC.getText().toString());
                        edited.put("ui",userUIC.getText().toString());
                        edited.put("phone",userPhoneC.getText().toString());
                        edited.put("address",userAddressC.getText().toString());
                        edited.put("intro",selfIntroC.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Personal_Info.this, "資料已更新。", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                //finish();
                            }
                        });
                        //Toast.makeText(Personal_Info.this, "郵件已更新。", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Toast.makeText(Personal_Info.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),WanHua.class);
                intent.putExtra("TextName",userNameC.getText().toString());
                //intent.putExtra("TextPhone",userPhoneC.getText().toString());


                startActivity(intent);
            }
        });
        missionBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),WanHua2.class);
                startActivity(intent);
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