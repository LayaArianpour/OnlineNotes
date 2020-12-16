package com.example.onlinenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private TextView txt_SignOut,txt_username;
    private FloatingActionButton fab_addNote;
    ImageView img_editItem,img_deleteItem;
    RecyclerView rv_view_nots;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_SignOut=findViewById(R.id.txt_SignOut);
        txt_username=findViewById(R.id.txt_username);
        fab_addNote=findViewById(R.id.fab_addNote);
        rv_view_nots=findViewById(R.id.rv_view_nots);

        img_editItem=findViewById(R.id.img_editItem);
        img_deleteItem=findViewById(R.id.img_deleteItem);

        fab_addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewActivity.class));
                finish();
            }
        });



    }

}