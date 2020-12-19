package com.example.onlinenotes.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinenotes.R;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_username=(itemView).findViewById(R.id.txt_username);
        }
    }
}
