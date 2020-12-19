package com.example.onlinenotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinenotes.Adapter.NotesAdapter;
import com.example.onlinenotes.Adapter.UsersAdapter;
import com.example.onlinenotes.Model.Notes;
import com.example.onlinenotes.Model.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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

    ArrayList<Notes> mNotes;
    NotesAdapter notesAdapter;
    ArrayList<Users> mUser;
    UsersAdapter usersAdapter;

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

        mNotes=new ArrayList<Notes>();
        mUser=new ArrayList<Users>();
        rv_view_nots.setLayoutManager(new LinearLayoutManager(this));


        readNotes();
        readUser();




        fab_addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewActivity.class));
                finish();
            }
        });


    }

    private void readUser() {
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        mUser.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Users user= snapshot.getValue(Users.class);
                    mUser.add(user);
                    txt_username.setText(user.getUserName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readNotes() {
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Notes").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
       /* databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Notes note= snapshot.getValue(Notes.class);
                    mNotes.add(note);
                }
                rv_view_nots.setAdapter(notesAdapter);
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mNotes.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Notes note= dataSnapshot.getValue(Notes.class);
                    mNotes.add(note);
                }
                notesAdapter=new NotesAdapter(MainActivity.this,mNotes);
                rv_view_nots.setAdapter(notesAdapter);
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}