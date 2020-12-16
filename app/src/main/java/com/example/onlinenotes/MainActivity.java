package com.example.onlinenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinenotes.Adapter.UsersAdapter;
import com.example.onlinenotes.Model.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TextView txt_SignOut,txt_username;
    private FloatingActionButton fab_addNote;
    ImageView img_editItem,img_deleteItem;
    RecyclerView rv_view_nots;

    DatabaseReference databaseReference;
    //DatabaseReference databaseReferenceUserName;

    ArrayList<Users> notesList;
    UsersAdapter notesAdapter;
    //ArrayList<Users> usersList;
    //UsersAdapter usersAdapter;


    DatabaseReference databaseReferenceUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_SignOut=findViewById(R.id.txt_SignOut);
        fab_addNote=findViewById(R.id.fab_addNote);
        rv_view_nots=findViewById(R.id.rv_view_nots);
        txt_username=findViewById(R.id.txt_username);
        img_editItem=findViewById(R.id.img_editItem);
        img_deleteItem=findViewById(R.id.img_deleteItem);

        rv_view_nots.setLayoutManager(new LinearLayoutManager(this));
        notesList=new ArrayList<Users>();

        //databaseReferenceUserName=FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Notes");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Users note=dataSnapshot.getValue(Users.class);
                        notesList.add(note);
                }
                notesAdapter=new UsersAdapter(MainActivity.this,notesList);
                rv_view_nots.setAdapter(notesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Opsss database error....",Toast.LENGTH_SHORT).show();

            }
        });

        /*databaseReferenceUserName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Users user = dataSnapshot.getValue(Users.class);
                    txt_username.setText(user.getUserName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Opsss database error....",Toast.LENGTH_SHORT).show();

            }
        });*/

        fab_addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewActivity.class));
                finish();
            }
        });



    }

}