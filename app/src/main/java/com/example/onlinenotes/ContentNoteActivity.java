package com.example.onlinenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class ContentNoteActivity extends AppCompatActivity {

    TextView txt_titleNoteContent,txt_noteContent;
    ImageView img_editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_note);

        txt_titleNoteContent=findViewById(R.id.txt_titleNoteContent);
        txt_noteContent=findViewById(R.id.txt_noteContent);
        img_editContent=findViewById(R.id.img_editContent);


        String title=getIntent().getStringExtra("title");
        String textNote=getIntent().getStringExtra("textNote");
        String noteId=getIntent().getStringExtra("noteId");

        txt_titleNoteContent.setText(title);
        txt_noteContent.setText(textNote);


        img_editContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContentNoteActivity.this,EditActivity.class);
                intent.putExtra("title",txt_titleNoteContent.getText());
                intent.putExtra("textNote",txt_noteContent.getText());
                intent.putExtra("noteId",noteId);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


            }
        });

    }
}