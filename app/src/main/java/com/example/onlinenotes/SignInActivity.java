package com.example.onlinenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText edit_emailSignIn,edit_passSignIn;
    private ImageView img_goSignIn;
    private MaterialTextView txt_createAccountSignIn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edit_emailSignIn=findViewById(R.id.edit_emailSignIn);
        edit_passSignIn=findViewById(R.id.edit_passSignIn);
        img_goSignIn=findViewById(R.id.img_goSignIn);
        txt_createAccountSignIn=findViewById(R.id.txt_createAccountSignIn);

        firebaseAuth=FirebaseAuth.getInstance();

        txt_createAccountSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartActivity=new Intent(SignInActivity.this,SignUpActivity.class);
                intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentStartActivity);
                finish();
            }
        });

        img_goSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=edit_emailSignIn.getText().toString();
                String pass=edit_passSignIn.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                    Toast.makeText(SignInActivity.this,"Email Or Password is Empty!",Toast.LENGTH_SHORT).show();
                }
                else{
                    SignIn(email,pass);
                }
            }
        });


    }

    private void SignIn(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignInActivity.this,"SignIn is Successful",Toast.LENGTH_SHORT).show();
                    Intent intentStartActivity=new Intent(SignInActivity.this,MainActivity.class);
                    intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentStartActivity);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignInActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}