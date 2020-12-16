package com.example.onlinenotes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinenotes.Model.Users;
import com.example.onlinenotes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    Context context;
    ArrayList<Users> users;
    private FirebaseUser firebaseUser;

    public UsersAdapter(Context context, ArrayList<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);
        return new UsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        Users user=users.get(position);
        holder.txt_titleNoteItem.setText(user.getTitle());
        holder.txt_dateNoteItem.setText(user.getDateTime());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_titleNoteItem;
        public TextView txt_dateNoteItem;
        public ImageView img_deleteItem;
        public ImageView img_editItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_titleNoteItem=(itemView).findViewById(R.id.txt_titleNoteItem);
            txt_dateNoteItem=(itemView).findViewById(R.id.txt_dateNoteItem);
            img_deleteItem=(itemView).findViewById(R.id.img_deleteItem);
            img_editItem=(itemView).findViewById(R.id.img_editItem);
        }
    }
}
