package com.example.onlinenotes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinenotes.MainActivity;
import com.example.onlinenotes.Model.Notes;
import com.example.onlinenotes.Model.Users;
import com.example.onlinenotes.R;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    DatabaseReference databaseReference;
    Context context;
    ArrayList<Notes> mNotes;


    public NotesAdapter(Context context, ArrayList<Notes> mNotes) {
        this.context = context;
        this.mNotes = mNotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);
        return new NotesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes note=mNotes.get(position);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Notes").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        holder.txt_titleNoteItem.setText(note.getTitle());
        holder.txt_dateNoteItem.setText(note.getDateTime());


        holder.img_deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query queryDel=databaseReference.orderByChild("noteId").equalTo(note.getNoteId());
                queryDel.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            dataSnapshot.getRef().removeValue();
                            Toast.makeText(context,"Delete Note is Successful",Toast.LENGTH_SHORT).show();

                        }
                        //Toast.makeText(context,"out for",Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //Toast.makeText(context,"no del",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_titleNoteItem;
        public TextView txt_dateNoteItem;
        public ImageView img_deleteItem;
        public ImageView img_editItem;
        public LinearLayout container_noteItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_titleNoteItem=(itemView).findViewById(R.id.txt_titleNoteItem);
            txt_dateNoteItem=(itemView).findViewById(R.id.txt_dateNoteItem);
            img_deleteItem=(itemView).findViewById(R.id.img_deleteItem);
            img_editItem=(itemView).findViewById(R.id.img_editItem);
            container_noteItem=(itemView).findViewById(R.id.container_noteItem);
        }
    }
}
