package com.example.onlinenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText edit_fullnameSignUp,edit_usernameSignUp,edit_emailSignUp,edit_passSignUp;
    private ImageView img_goSignUp;
    private MaterialTextView txt_signInAccountSignUp;

    ProgressDialog pd;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edit_fullnameSignUp=findViewById(R.id.edit_fullnameSignUp);
        edit_usernameSignUp=findViewById(R.id.edit_usernameSignUp);
        edit_emailSignUp=findViewById(R.id.edit_emailSignUp);
        edit_passSignUp=findViewById(R.id.edit_passSignUp);
        img_goSignUp=findViewById(R.id.img_goSignUp);
        txt_signInAccountSignUp=findViewById(R.id.txt_signInAccountSignUp);

        pd=new ProgressDialog(this);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();


        img_goSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName=edit_fullnameSignUp.getText().toString();
                String userName=edit_usernameSignUp.getText().toString();
                String email=edit_emailSignUp.getText().toString();
                String pass=edit_passSignUp.getText().toString();

                if(TextUtils.isEmpty(fullName) || TextUtils.isEmpty(userName) ||
                   TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUpActivity.this,"Empty Fields!",Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<6){
                    Toast.makeText(SignUpActivity.this,"password too short!",Toast.LENGTH_SHORT).show();
                }
                else{
                    signUp(fullName,userName,email,pass);
                }
            }
        });

        txt_signInAccountSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartActivity=new Intent(SignUpActivity.this,SignInActivity.class);
                intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentStartActivity);
                finish();
            }
        });



    }

    private void signUp(String fullName, String userName, String email, String pass) {
        pd.setMessage("Please Wait");
        pd.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("fullName",fullName);
                map.put("userName",userName);
                map.put("email",email);
                map.put("id",firebaseAuth.getCurrentUser().getUid());

                databaseReference.child("Users").child(firebaseAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(SignUpActivity.this,"SignUp is Successful",Toast.LENGTH_SHORT).show();
                            Intent intentStartActivity=new Intent(SignUpActivity.this,MainActivity.class);
                            intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intentStartActivity);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(SignUpActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}