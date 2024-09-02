package com.example.a31;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Button registerBtn;
    private EditText registerName,registerUI,registerEmail,registerPassword,conPassword,registerPhone,registerAddress;
    private TextView gotoLogin;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerName    =findViewById(R.id.registerName);
        registerUI      =findViewById(R.id.registerUI);
        registerEmail   =findViewById(R.id.registerEmail);
        registerPassword=findViewById(R.id.registerPassword);
        conPassword     =findViewById(R.id.conPassword);
        registerPhone   =findViewById(R.id.registerPhone);
        registerAddress  =findViewById(R.id.registerAdress);

        gotoLogin  =findViewById(R.id.gotoLogin);
        registerBtn=findViewById(R.id.registerBtn);

        fAuth =FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    = registerEmail.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();

                String fullName = registerName.getText().toString();
                String ui       = registerUI.getText().toString();
                String conPass  = conPassword.getText().toString();
                String phone    = registerPhone.getText().toString();
                String address  = registerAddress.getText().toString();

                if (fullName.isEmpty()){
                    registerName.setError("請輸入需要全名");
                    return;
                }
                if (ui.isEmpty()){
                    registerUI.setError("請輸入身份證號碼");
                    return;
                }
                if(email.isEmpty()){
                    registerEmail.setError("請輸入電子郵件");
                    return;
                }
                if(password.isEmpty()){
                    registerPassword.setError("請輸入密碼");
                    return;
                }
                if(password.length()<6){
                    registerPassword.setError("密碼不可少於6");
                    return;
                }
                if (conPass.isEmpty()){
                    registerName.setError("確認密碼");
                    return;
                }
                if(!password.equals(conPass)){
                    conPassword.setError("密碼不對");
                    return;
                }
                if (phone.isEmpty()){
                    registerPhone.setError("請輸入手機號碼");
                    return;
                }
                if (address.isEmpty()){
                    registerAddress.setError("請輸入地址");
                    return;
                }



                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "註冊成功", Toast.LENGTH_SHORT).show();

                            userID =fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fullName",fullName);
                            user.put("email",email);
                            user.put("password",password);
                            user.put("ui",ui);
                            user.put("phone",phone);
                            user.put("address",address);
                            user.put("intro","點擊輸入自我介紹。");
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onSuccess: user Profile is created for "+ userID);
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Register.this, "失敗 !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });


    }
}














