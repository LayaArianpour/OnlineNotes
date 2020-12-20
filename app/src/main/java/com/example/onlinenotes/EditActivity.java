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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class EditActivity extends AppCompatActivity {

    TextInputEditText edit_titleNoteEdit,edit_noteEdit;
    ImageView img_saveEdit,img_discardEdit;
    DatabaseReference databaseReferenceEdit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edit_titleNoteEdit=findViewById(R.id.edit_titleNoteEdit);
        edit_noteEdit=findViewById(R.id.edit_noteEdit);
        img_saveEdit=findViewById(R.id.img_saveEdit);
        img_discardEdit=findViewById(R.id.img_discardEdit);

        String title=getIntent().getStringExtra("title");
        String textNote=getIntent().getStringExtra("textNote");
        String noteId=getIntent().getStringExtra("noteId");



        edit_titleNoteEdit.setText(title);
        edit_noteEdit.setText(textNote);
        
        img_saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edit_titleNoteEdit.getText())){
                    Toast.makeText(EditActivity.this,"Title is Empty!",Toast.LENGTH_SHORT).show();
                }
                else {
                    deleteNote(noteId);
                    String myTitleNote=edit_titleNoteEdit.getText().toString();
                    String myTextnote=edit_noteEdit.getText().toString();
                    saveNote(myTitleNote,myTextnote);
                    Intent intentStartActivity=new Intent(EditActivity.this,MainActivity.class);
                    intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentStartActivity);
                    finish();
                }
            }
        });

        img_discardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartActivity=new Intent(EditActivity.this,MainActivity.class);
                intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentStartActivity);
                finish();
            }
        });


    }

    private void deleteNote(String noteId) {
        databaseReferenceEdit= FirebaseDatabase.getInstance().getReference().child("Notes").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query queryDel=databaseReferenceEdit.orderByChild("noteId").equalTo(noteId);
        queryDel.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void saveNote( String titleNote, String textNote) {
        databaseReferenceEdit= FirebaseDatabase.getInstance().getReference();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSX");
        String dateTime= OffsetDateTime.now(ZoneOffset.UTC).format(formatter);
        HashMap<String,Object> mapNote=new HashMap<>();
        mapNote.put("title",titleNote);
        mapNote.put("textNote",textNote);
        mapNote.put("dateTime",dateTime);
        mapNote.put("publisher", FirebaseAuth.getInstance().getCurrentUser().getUid());
        mapNote.put("noteId",databaseReferenceEdit.push().getKey());
        databaseReferenceEdit.child("Notes").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(databaseReferenceEdit.push().getKey()).setValue(mapNote).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(EditActivity.this,"Edit Note is Successful",Toast.LENGTH_SHORT).show();
                    Intent intentStartActivity=new Intent(EditActivity.this,MainActivity.class);
                    intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentStartActivity);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                Intent intentStartActivity=new Intent(EditActivity.this,MainActivity.class);
                intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentStartActivity);
                finish();
            }
        });

    }

    

}