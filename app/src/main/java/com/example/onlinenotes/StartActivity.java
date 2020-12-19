package com.example.onlinenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private ImageView img_signInStart,img_signUpStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        img_signUpStart=findViewById(R.id.img_signUpStart);
        img_signInStart=findViewById(R.id.img_signInStart);

        img_signInStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartActivity=new Intent(StartActivity.this,SignInActivity.class);
                intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentStartActivity);
                finish();
            }
        });

        img_signUpStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartActivity=new Intent(StartActivity.this,SignUpActivity.class);
                intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentStartActivity);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intentStartActivity=new Intent(StartActivity.this,MainActivity.class);
            intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentStartActivity);
            finish();
        }
    }
}