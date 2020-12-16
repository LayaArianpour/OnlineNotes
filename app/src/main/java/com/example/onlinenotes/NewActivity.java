package com.example.onlinenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.sql.Time;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class NewActivity extends AppCompatActivity {

    private TextInputEditText edit_titleNoteNew,edit_noteNew;
    private ImageView img_saveNew,img_discardNew;

    private DatabaseReference databaseReference;
    private Date date;
    private Time time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        edit_titleNoteNew=findViewById(R.id.edit_titleNoteNew);
        edit_noteNew=findViewById(R.id.edit_noteNew);
        img_saveNew=findViewById(R.id.img_saveNew);
        img_discardNew=findViewById(R.id.img_discardNew);

        databaseReference= FirebaseDatabase.getInstance().getReference();

        img_discardNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewActivity.this,MainActivity.class));
                finish();
            }
        });

        img_saveNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleNote=edit_titleNoteNew.getText().toString();
                String textNote=edit_noteNew.getText().toString();

                if(titleNote.isEmpty()){
                    Toast.makeText(NewActivity.this,"Title is Empty!",Toast.LENGTH_SHORT).show();
                }
                else{
                    addNote(titleNote,textNote);
                }
            }
        });

    }

    private void addNote(String titleNote, String textNote) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSX");
        String currentDateTimeString = OffsetDateTime.now(ZoneOffset.UTC).format(formatter);
        HashMap<String,Object> mapNote=new HashMap<>();
        mapNote.put("title",titleNote);
        mapNote.put("textNote",textNote);
        mapNote.put("DateTime",currentDateTimeString);
        mapNote.put("publisher", FirebaseAuth.getInstance().getCurrentUser().getUid());
        mapNote.put("noteId",databaseReference.push().getKey());
        databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Notes").child(databaseReference.push().getKey()).setValue(mapNote).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(NewActivity.this,"Save Note is Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewActivity.this,MainActivity.class));
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NewActivity.this,MainActivity.class));
                finish();
            }
        });
    }


}